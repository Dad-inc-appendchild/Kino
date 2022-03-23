let url = "http://127.0.0.1:8080"
let ticketholder = document.getElementById("tickets");
let rowCount;
let screeningid = 1;
createList(screeningid)

async function createList(id) {
  ticketholder.innerHTML = '';
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

  console.log(tickets);
  console.log(phonenumber);

  let customer = {
    customerId: '1',
    name: 'John',
    phoneNumber: '0011223344'
  }

  //handleCustomer(phonenumber);

  bookAndRefresh(customer, tickets);

});

async function bookAndRefresh(customer, tickets){
  await bookTicket(customer,tickets)
  await createList(screeningid);
}


let customerForm = document.getElementById("customerForm");
customerForm.addEventListener('submit', e => {
  e.preventDefault()

  let myModal = bootstrap.Modal.getInstance((document.getElementById('exampleModal')));
  let customer = {};
  customer.name = document.getElementById("customerName").value;
  customer.phoneNumber = document.getElementById("customerPhone").value;
  createNewCustomer(customer);

  customerForm.reset();
  myModal.hide();
})

async function handleCustomer(phonenumber) {
  let customer = await lookupCustomer(phonenumber);
  console.log(customer);
  if (null === customer) {
    let myModal = new bootstrap.Modal(document.getElementById('exampleModal'), {keyboard: false});
    myModal.show();

    customer = await customerInput(myModal);

    //TODO customer = createNewCustomer(customer);
  }

  //TODO confirm customer

  return customer
}

async function customerInput(modal) {

  // somehow wait for input in modal.
  // probably something promise and resolve in other event something like that.
  // modal events on hidden.bs.modal
}

async function lookupCustomer(phoneNumber) {
  let response = await fetch(url + "/api/customers/phonenumber={" + phoneNumber + "}");
  console.log(response);
  try {
    let json = await response.json();
    return json;
  } catch (e) {
    return null;
  }
}

async function createNewCustomer(customer) {
  console.log(customer)
  let response = await fetch(url + "/api/customers",
    {
      method: 'POST',
      headers: {
        'Content-type':'application/json'
      },
      body: JSON.stringify(customer)
    })
  let json = await response.json();

  return json;
}

async function bookTicket(customer, tickets){
  let stuff = {}
  stuff.customer = customer;
  stuff.tickets = tickets;
  console.log(stuff);
  let response = await fetch(url + "/api/tickets/book", {
    method:"POST",
    headers:{
      'Content-type': 'application/json'
    },
    body: JSON.stringify(stuff)
  })
}

