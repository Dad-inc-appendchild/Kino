//SETUP MENU/HEADER
async function loadHtmlTemplate(link, elementid) {
  let result = await fetch(link)
  let text = await result.text();

  let domparser = new DOMParser();
  let html = domparser.parseFromString(text, "text/html");

  if((f = html.body.querySelector('div')) !== null) {
    document.getElementById(elementid).append(f);
  }
}

let menuholder = document.getElementById("menu");

if(document.readyState === 'loading'){
  window.addEventListener('DOMContentLoaded', () => {
    buildPage();
  });
}else{
  buildPage();
}

async function buildPage(){
  loadHtmlTemplate("./html/navbar.html", "navbar");
  setupMenu()
}

//document.createDocumentFragment()
//Kan måske bruges i stedet for denne løsning.

//SETUP MENU/HEADER END
//_____________________________________________________________-

async function setupMenu() {
  //fetch goes here
  const response = await fetch('http://127.0.0.1:8080/api/products');
  const resJSON = await response.json();
  console.log(resJSON);
  resJSON.forEach(addItemToElement)
}

function addItemToElement(item){
  //find liste eller lav hvis mangler?
  let list = findItemGroup(item.itemGroup);
  list.append(createProduct(item.productName, item.price +" kr"));
}

function findItemGroup(itemgroup){
  if( document.getElementById(itemgroup) !== null){
    return document.getElementById(itemgroup);
  }else{
    console.log(itemgroup);
    createItemGroup(itemgroup);
    return findItemGroup(itemgroup);
  }
}

function createItemGroup(itemgroup){
  let container = document.createElement("div");
  container.classList.add("col")
  container.classList.add("col-lg-6")

  let row = document.createElement("div");
  row.classList.add("row");

  let wrapper = document.createElement("div");
  wrapper.classList.add("menu-wrapper");
  wrapper.id = itemgroup;

  let title = document.createElement("h4");
  title.classList.add("menu-title");
  title.innerText = itemgroup.toUpperCase();

  wrapper.append(title);
  row.append(wrapper);
  container.append(row);

  menuholder.append(container);
}

function createProduct(name, price){

let menuItem = document.createElement("div");
menuItem.classList.add("menu-item");

let menuContent = document.createElement("div");
menuContent.classList.add("menu-content");
menuContent.classList.add("row");

let coloumn1 = document.createElement("div");
coloumn1.classList.add("col-8");

let itemName = document.createElement("p");
itemName.innerText = name;
coloumn1.append(itemName);

menuContent.append(coloumn1);

let coloumn2 = document.createElement("div");
coloumn2.classList.add("col-4");
coloumn2.classList.add("menu-price");

let priceText = document.createElement("span");
priceText.innerText = price;

coloumn2.append(priceText);
menuContent.append(coloumn2);
menuItem.append(menuContent);

return menuItem;
}



