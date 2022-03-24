async function loadHtmlTemplate(link, elementid) {
  let result = await fetch(link)
  let text = await result.text();

  let domparser = new DOMParser();
  let html = domparser.parseFromString(text, "text/html");

  if ((f = html.body.querySelector('div')) !== null) {
    document.getElementById(elementid).append(f);
  }
}

if (document.readyState === 'loading') {
  window.addEventListener('DOMContentLoaded', () => {
    buildPage();
  });
} else {
  buildPage();
}

async function buildPage() {
  loadHtmlTemplate("./html/navbar.html", 'navbar');
  loadHtmlTemplate("./html/carusel.html", 'carusel');
  await loadHtmlTemplate("./html/movieContainer.html", 'moviecontainer');
  makeMovieList();
}

function buildMovie(movie) {
  console.log(movie.id)
  let movieContainer = document.createElement("div");
  movieContainer.classList.add("col");

  let movieElement = document.createElement("div");
  movieElement.id = movie.id;
  movieElement.classList.add("card");

  //image source goes here
  let imagesrc = movie.imagesrc;

  let image = document.createElement("img");
  image.src = imagesrc;
  image.classList.add("card-img-top");
  image.alt = "img/FrenchFriday.png";
  movieElement.append(image);

  let cardBody = document.createElement("div");
  cardBody.classList.add("card-body");
  //cardBody.classList.add("movie-body");
  let cardHeader = document.createElement("h5");
  cardHeader.innerText = movie.title; //title goes here;
  cardHeader.classList.add("movie-title");
  cardBody.append(cardHeader);

  let cardParagraph = document.createElement("p");
  cardParagraph.innerHTML = "INSERT MOVIE ATTRIBUT DESCRIPTION HERE";
  cardParagraph.classList.add("card-text");
  cardBody.append(cardParagraph);

  const cardButton = document.createElement("a");
  cardButton.innerHTML = "Læs mere";
  cardButton.href = "#";
  cardButton.classList.add("btn");
  cardButton.classList.add("btn-outline-primary");
  cardBody.append(cardButton);

  const cardButtonLink = document.createElement("a");
  cardButtonLink.innerHTML = "Se trailer";
  cardButtonLink.href = "#";
  cardButtonLink.classList.add("btn");
  cardButtonLink.classList.add("btn-outline-secondary");
  cardBody.append(cardButtonLink);

  movieElement.append(cardBody);

  let cardList = document.createElement("ul"); //screenings?
  cardList.classList.add("list-group");
  cardList.classList.add("list-group-flush");

  /*for (let i = 0; i < 3; i++) { //change to for each screening?
    let cardElement = document.createElement("li");
    cardElement.classList.add("list-group-item");
    cardElement.innerText = "Screening " + i;
    cardList.append(cardElement);
  }

   */



  movieElement.append(cardBody);

  movieContainer.append(movieElement);
  return movieContainer;



}

function makeMovieList() {
  fetchMovies();
}

function appendToList(movie) {
  let movieContainer = document.getElementById("movies");
  movieContainer.append(buildMovie(movie));
}

async function fetchMovies() {
  const response = await fetch('http://127.0.0.1:8080/api/movies');
  const resJSON = await response.json();
  console.log(resJSON);
  resJSON.forEach(appendToList);
}
