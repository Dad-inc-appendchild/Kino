'use strict';

//SETUP MENU/HEADER
async function loadHtmlTemplate(link, elementid) {
  let result = await fetch(link)
  let text = await result.text();

  let domparser = new DOMParser();
  let html = domparser.parseFromString(text, "text/html");

  let f;
  if ((f = html.body.querySelector('div')) !== null) {
    document.getElementById(elementid).append(f);
  }
}

document.getElementById("menu");
if (document.readyState === 'loading') {
  window.addEventListener('DOMContentLoaded', () => {
    buildPage();
  });
} else {
  buildPage();
}

async function buildPage() {
  await loadHtmlTemplate("./html/navbar.html", "navbar");
}

const cal = {
  // (A) PROPERTIES
  // (A1) COMMON CALENDAR
  sMon: false, // Week start on Monday?
  mName: ["Januar", "Februar", "Marts", "April", "Maj", "Juni", "Juli", "August", "September", "October", "November", "December"],

  // (A2) CALENDAR DATA
  data: null, // Events for the selected period
  sDay: 0, sMth: 0, sYear: 0, // Current selected day, month, year

  // (A3) COMMON HTML ELEMENTS
  hMth: null, hYear: null, // month/year selector
  hForm: null, hfHead: null, hfDate: null, hfTxt: null, hfDel: null, // event form

  // (B) INIT CALENDAR
  init: () => {
    // (B1) GET + SET COMMON HTML ELEMENTS
    cal.hMth = document.getElementById("cal-mth");
    cal.hYear = document.getElementById("cal-yr");
    cal.hForm = document.getElementById("cal-event");
    cal.hfHead = document.getElementById("evt-head");
    cal.hfDate = document.getElementById("evt-date");
    // cal.hfTxt = document.getElementById("evt-details");
    cal.hfDel = document.getElementById("evt-del");
    document.getElementById("evt-close").onclick = cal.close;

    // (B2) DATE NOW
    let now = new Date(), nowMth = now.getMonth(), nowYear = parseInt(now.getFullYear());

    // (B3) APPEND MONTHS SELECTOR
    for (let i = 0; i < 12; i++) {
      let opt = document.createElement("option");
      opt.value = i;
      opt.innerHTML = cal.mName[i];
      if (i === nowMth) {
        opt.selected = true;
      }
      cal.hMth.appendChild(opt);
    }
    cal.hMth.onchange = cal.list;

    // (B4) APPEND YEARS SELECTOR
    // Set to 10 years range. Change this as you like.
    for (let i = nowYear - 1; i <= nowYear + 3; i++) {
      let opt = document.createElement("option");
      opt.value = i;
      opt.innerHTML = i;
      if (i === nowYear) {
        opt.selected = true;
      }
      cal.hYear.appendChild(opt);
    }
    cal.hYear.onchange = cal.list;

    // (B5) START - DRAW CALENDAR
    cal.list();
  },

  // (C) DRAW CALENDAR FOR SELECTED MONTH
  list: async () => {
    // (C1) BASIC CALCULATIONS - DAYS IN MONTH, START + END DAY
    // Note - Jan is 0 & Dec is 11
    // Note - Sun is 0 & Sat is 6
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

    // (C4) DRAW HTML CALENDAR
    // Get container
    let container = document.getElementById("cal-container"), cTable = document.createElement("table");
    cTable.id = "calendar";
    container.innerHTML = "";
    container.appendChild(cTable);

    // First row - Day names
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
    for (let i = 0; i < total; i++) {

      let cCell = document.createElement("td");
      if (squares[i] === "b") {
        cCell.classList.add("blank");
      } else {

        const screenings = await getScreenings(squares[i]); // TODO refactor -> only call backend once

        if (Object.keys(screenings).length !== 0) {
          cCell.classList.add("teal-600");
        }


        if (nowDay === squares[i]) {
          cCell.classList.add("text-grey-600");
        }
        cCell.innerHTML = `<div class="dd text-dark text-center fs-4">${squares[i]}</div>`;
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

    // (C5) REMOVE ANY PREVIOUS ADD/EDIT EVENT DOCKET
    cal.close();
  },

  // (D) SHOW EDIT EVENT DOCKET FOR SELECTED DAY
  show: (el) => {
    // (D1) FETCH EXISTING DATA
    cal.sDay = el.getElementsByClassName("dd")[0].innerHTML;
    cal.hfHead.innerHTML = "Dagens forestillinger";


    showAllScreenings();

    cal.hfDate.innerHTML = `${cal.sDay} ${cal.mName[cal.sMth]} ${cal.sYear}`;

    cal.hForm.classList.remove("ninja");

  },

  // (E) CLOSE EVENT DOCKET
  close: () => {
    cal.hForm.classList.add("ninja");
  },

};

async function getScreenings(day) {

  const date = `${cal.sYear}-${("0" + (cal.sMth + 1)).slice(-2)}-${("0" + (day)).slice(-2)}`; // yyyy-mm-dd

  const url = "http://localhost:8080/api/screenings/date/" + date;

  let response = await fetch(url);
  return await response.json();
}

async function getTicketsByScreeningId(screeningId) {
  const url = "http://localhost:8080/api/tickets/screenings/" + screeningId;

  let response = await fetch(url);
  return await response.json();
}

async function showAllScreenings() {
  const table = document.getElementById("screening-table");
  const screenings = await getScreenings(cal.sDay);

  table.innerHTML = "";

  if (Object.keys(screenings).length !== 0) {
    let data = Object.keys(screenings[0]);

    generateTableHead(table, data);
    generateTable(table, screenings);
  }
}

function generateTableHead(table) {
  let thead = table.createTHead();
  let row = thead.insertRow();

  let thList = ["Start tid", "Slut tid", "Sal", "Film", "Event", "Bookinger"];
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

    let bookings = 0;
    let seats = 0;
    tickets.forEach(function (el) {
        seats++
      if (null !== el.customer) {
        bookings++
      }
    });

    const rowList = [
      screening.startTime.slice(11, 16),
      screening.endTime.slice(11, 16),
      screening.kinoHall.kinoHallId,
      screening.movie.title,
      screening.event ?? "--",  // ?? = if null
      bookings + " / " + seats
    ];

    let cell, text;

    rowList.forEach(el => {
      cell = row.insertCell();
      text = document.createTextNode(el);
      cell.appendChild(text);
    })
  }
}

window.addEventListener("load", cal.init);
