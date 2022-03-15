
async function loadnavbar() {
  result = await fetch("./html/navbar.html")
  text = await result.text();

  domparser = new DOMParser();
  html = domparser.parseFromString(text, "text/html");

  if((f = html.body.querySelector('nav')) !== null) {
    document.getElementById('navbar').append(f);
  }
}

if(document.readyState === 'loading'){
  window.addEventListener('DOMContentLoaded', () => {loadnavbar()});
}else{
  loadnavbar();
}
