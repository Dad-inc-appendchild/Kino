'use strict';

// Setup header
async function loadHtmlTemplate(link, elementid) {
  let result = await fetch(link)
  let text = await result.text();

  let domparser = new DOMParser();
  let html = domparser.parseFromString(text, "text/html");

  let f;
  if ((f = html.body.querySelector("div")) !== null) {
    document.getElementById(elementid).append(f);
  }
}

document.getElementById("menu");
if (document.readyState === "loading") {
  window.addEventListener("DOMContentLoaded", () => {
    buildPage();
  });
} else {
  buildPage();
}

async function buildPage() {
  await loadHtmlTemplate("./html/navbar.html", "navbar");
}

const cal = {
  sMon: false, // Week start on Monday?
  mName: ["Januar", "Februar", "Marts", "April", "Maj", "Juni", "Juli", "August", "September", "October", "November", "December"],

  data: null, // Events for the selected period
  sDay: 0, sMth: 0, sYear: 0, // Current selected day, month, year

  hMth: null, hYear: null, // month/year selector

  hForm: null, hfHead: null, hfDate: null, hfTxt: null, hfDel: null, // event form

  // Init calendar
  init: () => {
    initLoader.show();
    cal.hMth = document.getElementById("cal-mth");
    cal.hYear = document.getElementById("cal-yr");
    cal.hForm = document.getElementById("cal-event");
    cal.hfHead = document.getElementById("evt-head");
    cal.hfDate = document.getElementById("evt-date");

    // Date now
    let now = new Date(), nowMth = now.getMonth(), nowYear = parseInt(now.getFullYear());

    // Append month selector
    let i;
    for (i = 0; i < 12; i++) {
      let opt = document.createElement("option");
      opt.value = i;
      opt.innerHTML = cal.mName[i];
      if (i === nowMth) {
        opt.selected = true;
      }
      cal.hMth.appendChild(opt);
    }
    cal.hMth.onchange = cal.list;

    let j;
    for (j = nowYear - 1; j <= nowYear + 3; j++) {
      let opt = document.createElement("option");
      opt.value = j;
      opt.innerHTML = j;
      if (j === nowYear) {
        opt.selected = true;
      }
      cal.hYear.appendChild(opt);
    }
    cal.hYear.onchange = cal.list;


    // Draw calendar

    cal.list();

  },

  // Draw calendar for selected month
  list: async () => {

    cal.sMth = parseInt(cal.hMth.value); // selected month
    cal.sYear = parseInt(cal.hYear.value); // selected year
    let daysInMth = new Date(cal.sYear, cal.sMth + 1, 0).getDate(), // number of days in selected month
      startDay = new Date(cal.sYear, cal.sMth, 1).getDay(), // first day of the month
      endDay = new Date(cal.sYear, cal.sMth, daysInMth).getDay(), // last day of the month
      now = new Date(), // current date
      nowMth = now.getMonth(), // current month
      nowYear = parseInt(now.getFullYear()), // current year
      nowDay = cal.sMth === nowMth && cal.sYear === nowYear ? now.getDate() : null;

    // (C2) LOAD DATA FROM LOCALSTORAGE
    cal.data = localStorage.getItem("cal-" + cal.sMth + "-" + cal.sYear);
    if (cal.data == null) {
      localStorage.setItem("cal-" + cal.sMth + "-" + cal.sYear, "{}");
      cal.data = {};
    } else {
      cal.data = JSON.parse(cal.data);
    }

    // (C3) DRAWING CALCULATIONS
    // Blank squares before start of month
    let squares = [];
    cal.sMon = true;
    if (cal.sMon && startDay !== 1) {
      let blanks = startDay === 0 ? 7 : startDay;
      for (let i = 1; i < blanks; i++) {
        squares.push("b");
      }
    }
    if (!cal.sMon && startDay !== 0) {
      for (let i = 0; i < startDay; i++) {
        squares.push("b");
      }
    }

    // Days of the month
    for (let i = 1; i <= daysInMth; i++) {
      squares.push(i);
    }

    // Blank squares after end of month
    if (cal.sMon && endDay !== 0) {
      let blanks = endDay === 6 ? 1 : 7 - endDay;
      for (let i = 0; i < blanks; i++) {
        squares.push("b");
      }
    }
    if (!cal.sMon && endDay !== 6) {
      let blanks = endDay === 0 ? 6 : 6 - endDay;
      for (let i = 0; i < blanks; i++) {
        squares.push("b");
      }
    }

    // Draw calender
    let container = document.getElementById("cal-container"), cTable = document.createElement("table");
    cTable.id = "calendar";
    container.innerHTML = "";
    container.appendChild(cTable);

    // Create head row of day names
    let cRow = document.createElement("tr"),
      days = ["Søndag", "Mandag", "Tirsdag", "Onsdag", "Torsdag", "Fredag", "Lørdag"];
    if (cal.sMon) {
      days.push(days.shift());
    }

    let d;
    for (d of days) {
      let cCell = document.createElement("td");
      cCell.innerHTML = d;
      cRow.appendChild(cCell);
    }
    cRow.classList.add("head");
    cTable.appendChild(cRow);

    // Days in Month
    let total = squares.length;
    cRow = document.createElement("tr");

    cRow.classList.add("day");

    const screenings = await getScreeningsByMonth(nowDay); // TODO refactor -> only call backend once
    console.log(screenings)

    let i;
    for (i = 0; i < total; i++) {
      let cCell = document.createElement("td");
      if (squares[i] === "b") {
        cCell.classList.add("blank");
      } else {

        if (hasScreenings(screenings, squares[i]) === true) {
          cCell.classList.add("teal-600");
        }

        if (nowDay === squares[i]) {
          cCell.id = "today";
          cCell.classList.add("today");
        }
        cCell.innerHTML = `<div class="dd text-light text-center fs-4">${squares[i]}</div>`;
        if (cal.data[squares[i]]) {
          cCell.innerHTML += "<div class='evt'>" + cal.data[squares[i]] + "</div>";
        }

        cCell.onclick = () => {
          cal.show(cCell);
        };
      }
      cRow.appendChild(cCell);
      if ((i + 1) % 7 === 0 && i !== 0) {
        cTable.appendChild(cRow);
        cRow = document.createElement("tr");
        cRow.classList.add("day");
      }
    }
    document.getElementById("today").click();
    initLoader.hide();
  },

  // Show event for selected day
  show: (el) => {
    clearSeatings();
    cal.sDay = el.getElementsByClassName("dd")[0].innerHTML;
    cal.hfHead.innerHTML = "Dagens forestillinger";
    showAllScreenings();
    cal.hfDate.innerHTML = `${cal.sDay} ${cal.mName[cal.sMth]} ${cal.sYear}`;
    cal.hForm.classList.remove("ninja");
  },
};


function hasScreenings(screenings, square) {
  for (let i = 0; i < screenings.length; i++) {
    const date =  new Date (screenings[i].startTime);
    if (date.getDate() === square){
      return true;
    }
  }
  return false;
}

async function getScreenings(day) {
  const date = `${cal.sYear}-${("0" + (cal.sMth + 1)).slice(-2)}-${("0" + (day)).slice(-2)}`; // yyyy-mm-dd

  const url = "http://localhost:8080/api/screenings/date/" + date;

  let response = await fetch(url + "api/screenings/date" + date);
  return response.json();
}

async function getScreeningsByMonth(day){
  const date = `${cal.sYear}-${("0" + (cal.sMth + 1)).slice(-2)}-${("0" + (day)).slice(-2)}`; // yyyy-mm-dd
  const url = "http://localhost:8080/api/screenings/month/" + date;
  let response = await fetch(url);
  return response.json();
}

async function getTicketsByScreeningId(screeningId) {
  let response = await fetch(url + "/api/screenings/ " + screeningId + "/tickets");
  return response.json();
}

async function showAllScreenings() {
  const table = document.getElementById("screening-table");
  const screenings = await getScreenings(cal.sDay);

  table.innerHTML = "";

  if (Object.keys(screenings).length !== 0) {
    generateTableHead(table);
    generateTable(table, screenings);
  }

}

function generateTableHead(table) {
  let thead = table.createTHead();
  let row = thead.insertRow();

  let thList = ["Start tid", "Slut tid", "Sal", "Film", "Event", "Bookinger", ""];
  let th;
  let text;

  thList.forEach(el => {
    th = document.createElement("th");
    text = document.createTextNode(el);
    th.appendChild(text);
    row.appendChild(th);
  });
}

async function generateTable(table, data) {
  for (let screening of data) {
    let row = table.insertRow();

    let tickets = await getTicketsByScreeningId(screening.screeningId);

    let bookings = 0, seats = 0;
    tickets.forEach(function (el) {
      seats++
      if (null !== el.customer) {
        bookings++
      }
    });


    const columnList = [screening.startTime.slice(11, 16), screening.endTime.slice(11, 16), screening.kinoHall.kinoHallId, screening.movie.title, screening.event ? screening.event : "--", bookings + " / " + seats];

    let cell, text;

    columnList.forEach(el => {
      cell = row.insertCell();
      text = document.createTextNode(el);
      cell.appendChild(text);
    });

    const btn = document.createElement("button");
    btn.textContent = "Book sæder";
    btn.id = screening.screeningId;
    btn.classList.add("btn");
    btn.classList.add("btn-outline-secondary");
    btn.classList.add("btn-calender");

    btn.onclick = () => {
      createList(btn.id);
    }

    cell = row.insertCell();
    cell.appendChild(btn);
  }
}

window.addEventListener("load", cal.init);
