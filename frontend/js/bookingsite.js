let url = "http://127.0.0.1:8080"
let ticketholder = document.getElementById("tickets");
let rowCount;
createList(1)

async function createList(id) {
  let response = await fetch(url +"/api/screenings/ " + id + "/tickets");
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
  console.log(input.data);

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
form.addEventListener('submit',e => {
  e.preventDefault();

  let values = Array.from(document.querySelectorAll('input[type=checkbox]:checked'))
    .map(item => item.data)
    .join(',');

  let phonenumber = document.getElementById("form-phone").value;

  console.log(values);
  console.log(phonenumber);

  let customer = lookupCustomer(phonenumber);
  if (customer == null){

    customer = createNewCustomer(customer);
  }
  //confirm customer

  //book ticket

});


async function lookupCustomer(phoneNumber){
  let response = await fetch(url +"/api/customers/phonenumber={" + phoneNumber + "}");
  console.log(response);
  let json = await response.json();

  return json
}

async function createNewCustomer(customer){
  let response = await fetch(url + "/api/customers/",
    {
      method:'POST',
      body: JSON.stringify(customer)
    })
  let json = await response.json();

  return json;
}

