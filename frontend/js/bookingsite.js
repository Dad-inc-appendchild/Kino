let ticketholder = document.getElementById("tickets");
let rowCount;
createList(1)


async function createList(id){
  let response = await fetch("http://127.0.0.1:8080/api/screenings/ " + id + "/tickets");
  let json = await response.json();

  console.log(json);
  json.forEach(appendTiList)
}


function appendTiList(ticket){
  ticketholder.append(ticketBuilder(ticket));
}

function ticketBuilder(ticket){
  let inputbox = document.createElement("div");

  let input = document.createElement("input");
  input.id="ticket" + ticket.ticketId;

  let label = document.createElement("label");
  label.for = input.id;

  label.classList.add("seats");
  if(ticket.seat.seatRow > rowCount){
    ticketholder.append(document.createElement("br"))
  }
  if(ticket.customer !== null){
    input.classList.add("seats-disabled")

  }else{
    label.classList.add("seats-available");
  }
  rowCount = ticket.seat.seatRow;
let span = document.createElement("span");
span.innerText = "test";
  label.append(span);

  inputbox.append(input);
  inputbox.append(label);

  return inputbox;
}

