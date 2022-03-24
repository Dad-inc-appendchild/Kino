'use strict';
let url = "http://127.0.0.1:8080"
let ticketholder = document.getElementById("tickets");
let rowCount;
let screeningid = 1;

function clearSeatings() {
  document.getElementById("seats-event").classList.add("ninja");
  ticketholder.innerHTML = '';
}

async function createList(id) {
  clearSeatings();
  document.getElementById("seats-event").classList.remove("ninja");

  let response = await fetch(url + "/api/screenings/ " + id + "/tickets");
  let json = await response.json();

  console.log(json);
  json.forEach(appendTiList)
}


function appendTiList(ticket) {
  ticketholder.append(ticketBuilder(ticket));
}

function ticketBuilder(ticket) {
  if (document.getElementById("row:" + ticket.seat.seatNumber) === null) {
    let newRow = document.createElement("div");
    newRow.classList.add("row");
    newRow.classList.add("row-booking");
    newRow.classList.add("seats-row");
    newRow.id = "row:" + ticket.seat.seatNumber;
    ticketholder.append(newRow);
  }
  let row = document.getElementById("row:" + ticket.seat.seatNumber);

  let inputbox = document.createElement("div");
  inputbox.classList.add("col");

  let input = document.createElement("input");
  input.id = "ticket:" + ticket.ticketId;
  input.type = "checkbox";
  input.data = ticket;

  let label = document.createElement("label");
  label.htmlFor = "ticket:" + ticket.ticketId;
  label.classList.add("seats");

  if (ticket.customer !== null) {
    label.classList.add("seats-disabled")
    input.disabled = true;

  } else {
    label.classList.add("seats-available");
  }
  rowCount = ticket.seat.seatRow;
  let span = document.createElement("span");
  span.classList.add("check");
  label.append(span);

  inputbox.append(input);
  inputbox.append(label);
  row.append(inputbox);

  return row;
}

const form = document.getElementById("form");
form.addEventListener('submit', e => {
  e.preventDefault();

  let tickets = Array.from(document.querySelectorAll('input[type=checkbox]:checked'))
    .map(item => item.data);

  let phonenumber = document.getElementById("form-phone").value;

  initBooking(phonenumber, tickets);

});

async function initBooking(phonenumber, tickets) {
  let customer = await handleCustomer(phonenumber);
  if (undefined !== customer) {
    await bookTicket(customer, tickets)
    await createList(screeningid);
  }
}

let customerForm = document.getElementById("customerForm");
customerForm.addEventListener('submit', async e => {
  e.preventDefault()

  let modal = document.getElementById('exampleModal');

  let bootstrapModal = bootstrap.Modal.getInstance(modal);
  let customer = {};
  customer.name = document.getElementById("customerName").value;
  customer.phoneNumber = document.getElementById("customerPhone").value;
  modal.data = await createNewCustomer(customer);

  customerForm.reset();
  bootstrapModal.hide();
})

async function handleCustomer(phonenumber) {
  let customer = await lookupCustomer(phonenumber);
  console.log(customer);
  if (null === customer) {
    let modal = document.getElementById('exampleModal');
    let boostrapModal = new bootstrap.Modal(modal, {keyboard: false});
    boostrapModal.show();
    document.getElementById("customerPhone").value = phonenumber;


    customer = await customerInput(modal);
  }
  //TODO confirm customer
  return customer
}

function customerInput(modal) {
  return new Promise((resolve) => {
    modal.addEventListener('hidden.bs.modal', async function () {
      let test = await modal.data;
      resolve(test);
    }, {once: true})
  })
}

async function lookupCustomer(phoneNumber) {
  let response = await fetch(url + "/api/customers/phonenumber={" + phoneNumber + "}");
  console.log(response);
  try {
    return await response.json();
  } catch (e) {
    return null;
  }
}

async function createNewCustomer(customer) {
  console.log(customer)
  let response = await fetch(url + "/api/customers", {
    method: 'POST', headers: {
      'Content-type': 'application/json'
    }, body: JSON.stringify(customer)
  })
  return response.json();
}

async function bookTicket(customer, tickets) {
  let stuff = {}
  stuff.customer = customer;
  stuff.tickets = tickets;
  console.log(stuff);
  let response = await fetch(url + "/api/tickets/book", {
    method: "POST", headers: {
      'Content-type': 'application/json'
    }, body: JSON.stringify(stuff)
  })
}

