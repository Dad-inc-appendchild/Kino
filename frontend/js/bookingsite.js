'use strict';
const ticketHolder = document.getElementById("tickets");

const bookingForm = document.getElementById("bookingForm");
bookingForm.addEventListener('submit', e => {
  e.preventDefault();

  let seats = Array.from(bookingForm.querySelectorAll('input[type=checkbox]:checked'))
    .map(item => item.data); //creates array with selected seats.

  let phoneNumber = document.getElementById("form-phone").value;

  initBooking(phoneNumber, seats);
});

const createCustomerForm = document.getElementById("customerForm");
createCustomerForm.addEventListener('submit', async e => {
  e.preventDefault() //prevents page refresh when modal closes.

  let modal = document.getElementById('exampleModal');
  let bootstrapModal = bootstrap.Modal.getInstance(modal);

  let customer = {
    name: document.getElementById("customerName").value,
    phoneNumber: document.getElementById("customerPhone").value
  };

  modal.data = await postNewCustomer(customer);
  createCustomerForm.reset();
  bootstrapModal.hide();
});


//Draw seat logic
function clearSeats() {
  document.getElementById("seats-event").classList.add("ninja");
  ticketHolder.innerHTML = '';
}

async function drawSeats(id) {
  clearSeats();
  document.getElementById("seats-event").classList.remove("ninja");
  ticketHolder.screeningId = id;

  let response = await fetch(url + "screenings/" + id + "/tickets");
  let json = await response.json();

  console.log(json);
  json.forEach(seatBuilder)
}

function seatBuilder(ticket) {
  if (document.getElementById("row:" + ticket.seat.seatNumber) === null) {
    createSeatRow(ticket.seat.seatNumber);
  }
  let row = document.getElementById("row:" + ticket.seat.seatNumber);
  let inputWrapper = document.createElement("div");
  inputWrapper.classList.add("col");

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
  let span = document.createElement("span");
  span.classList.add("check");
  label.append(span);

  inputWrapper.append(input);
  inputWrapper.append(label);
  row.append(inputWrapper);

  return row;
}

function createSeatRow(rowId){
  let newRow = document.createElement("div");
  newRow.classList.add("row");
  newRow.classList.add("row-booking");
  newRow.classList.add("seats-row");
  newRow.id = "row:" + rowId;
  ticketHolder.append(newRow);
}


//Booking logic
async function initBooking(phonenumber, tickets) {
  let customer = await handleCustomer(phonenumber);
  if (undefined !== customer) {
    await bookTicket(customer, tickets) //Possibility: Response for toast box confirming booking.
    await drawSeats(ticketHolder.screeningId);
    await showAllScreenings();
  }
}

async function bookTicket(customer, tickets) {
  let customerTicketsWrapper = {
  customer: customer,
  tickets: tickets
  }

  await fetch(url + "tickets/book", {
    method: "POST", headers: {
      'Content-type': 'application/json'
    }, body: JSON.stringify(customerTicketsWrapper)
  })
}


//Customer logic
async function handleCustomer(phonenumber) {
  let customer = await lookupCustomer(phonenumber);

  if (null === customer) {
    customer = await createCustomerInput(phonenumber);
  }
  //Possibility: Confirmation of customer can be added here.
  return customer
}

async function lookupCustomer(phoneNumber) {
  let response = await fetch(url + "customers/phonenumber={" + phoneNumber + "}");
  try {
    return await response.json();
  } catch (e) {
    return null;
  }
}

function createCustomerInput(phonenumber) {
  let modal = document.getElementById('exampleModal');
  let boostrapModal = new bootstrap.Modal(modal, {keyboard: false});
  document.getElementById("customerPhone").value = phonenumber;
  boostrapModal.show();

  return new Promise((resolve) => {
    modal.addEventListener('hidden.bs.modal', async function () {
      let customer = await modal.data;
      resolve(customer);
    }, {once: true})
  })
}

async function postNewCustomer(customer) {
  let response = await fetch(url + "customers", {
    method: 'POST', headers: {
      'Content-type': 'application/json'
    }, body: JSON.stringify(customer)
  })
  return response.json();
}
