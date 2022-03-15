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
    loadHtmlTemplate("./html/movieContainer.html", 'moviecontainer');
    loadHtmlTemplate("./html/movieCard.html", 'card1');
    loadHtmlTemplate("./html/movieCard.html", 'card2');
    loadHtmlTemplate("./html/movieCard.html", 'card3');
    loadHtmlTemplate("./html/movieCard.html", 'card4');

  });
}else{
  loadHtmlTemplate("./html/navbar.html", 'navbar');
  loadHtmlTemplate("./html/carusel.html", 'carusel');
  loadHtmlTemplate("./html/movieContainer.html", 'moviecontainer');
  loadHtmlTemplate("./html/movieCard.html", 'card1');
  loadHtmlTemplate("./html/movieCard.html", 'card2');
  loadHtmlTemplate("./html/movieCard.html", 'card3');
  loadHtmlTemplate("./html/movieCard.html", 'card4');
}
