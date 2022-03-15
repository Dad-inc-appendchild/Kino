async function loadHtmlTemplate(link, elementid) {
  result = await fetch(link)
  text = await result.text();

  domparser = new DOMParser();
  html = domparser.parseFromString(text, "text/html");

  if((f = html.body.querySelector('div')) !== null) {
    document.getElementById(elementid).append(f);
  }
}

if(document.readyState === 'loading'){
  window.addEventListener('DOMContentLoaded', () => {
    loadHtmlTemplate("./html/navbar.html", 'navbar');
    loadHtmlTemplate("./html/carusel.html", 'carusel');

  });
}else{
  loadHtmlTemplate("./html/navbar.html", 'navbar');
  loadHtmlTemplate("./html/carusel.html", 'carusel');
}
