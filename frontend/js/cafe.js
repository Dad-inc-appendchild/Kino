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

let productTest = { "product":[
    {
      id: "100",
      name: "test",
      price: "100kr",
      itemgroup: "snacks"
    },
    {
      id : "101",
      name: "test2",
      price : "250kr",
      itemgroup : "kaffe"
    },
    {
      id : "104",
      name: "Tapas",
      price : "2500kr",
      itemgroup : "Mad"
    },
    {
      id : "102",
      name: "Rød",
      price : "15000kr",
      itemgroup : "vino"
    }
  ]
}

if(document.readyState === 'loading'){
  window.addEventListener('DOMContentLoaded', () => {
    buildPage();
  });
}else{
  buildPage();
}

async function buildPage(){
  loadHtmlTemplate("./html/navbar.html", "navbar");
  setupmenu()
}

function setupmenu(){
  //fetch goes here
  productTest.product.forEach(addItemToElement);
}

function addItemToElement(item){
  //find liste eller lav hvis mangler?
  let list = finditemgroup(item.itemgroup);
  list.append(createproduct(item.name, item.price))
}


function finditemgroup(itemgroup){
  if( document.getElementById(itemgroup) !== null){
    return document.getElementById(itemgroup);
  }else{
    console.log(itemgroup);
    createitemgroup(itemgroup);
    return finditemgroup(itemgroup);
  }
}

function createitemgroup(itemgroup){
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


function createproduct(name, price){

let menuitem = document.createElement("div");
menuitem.classList.add("menu-item");

let menucontent = document.createElement("div");
menucontent.classList.add("menu-content");
menucontent.classList.add("row");

let coloumn1 = document.createElement("div");
coloumn1.classList.add("col-8");

let itemname = document.createElement("p");
itemname.innerText = name;
coloumn1.append(itemname);

menucontent.append(coloumn1);

let coloumn2 = document.createElement("div");
coloumn2.classList.add("col-4");
coloumn2.classList.add("menu-price");

let pricetext = document.createElement("span");
pricetext.innerText = price;

coloumn2.append(pricetext);
menucontent.append(coloumn2);
menuitem.append(menucontent);

return menuitem;
}



