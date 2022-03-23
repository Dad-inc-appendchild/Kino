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
  movieElement.classList.add("movie-width");

  //image source goes here
  let imagesrc = movie.imagesrc;

  let image = document.createElement("img");
  image.src = imagesrc;
  image.classList.add("card-img-top");
  image.classList.add("card-img-maxSize");
  image.alt = "img/FrenchFriday.png";
  movieElement.append(image);

  let cardBody = document.createElement("div");
  cardBody.classList.add("card-body");
  cardBody.classList.add("movie-body");
  let cardHeader = document.createElement("h5");
  cardHeader.innerText = movie.title; //title goes here;
  cardHeader.classList.add("movie-title");
  cardBody.append(cardHeader);

  let cardtext = document.createElement("p");
  cardtext.innerHTML = "Udgivelsesår: " + movie.year + "<br>" + "Spilletid: " + Math.round(movie.duration) + " Minutter"; //INSERT MORE DATA
  cardtext.classList.add("card-text");
  cardtext.classList.add("movie-text");
  cardBody.append(cardtext);
  movieElement.append(cardBody);

  let cardList = document.createElement("ul"); //screenings?
  cardList.classList.add("list-group");
  cardList.classList.add("list-group-flush");

  for (let i = 0; i < 3; i++) { //change to for each screening?
    let cardElement = document.createElement("li");
    cardElement.classList.add("list-group-item");
    cardElement.innerText = "Screening " + i;
    cardList.append(cardElement);
  }

  let trailerLink = movie.trailerLink;
  movieElement.append(cardList);
  cardBody = document.createElement("div");
  let link = document.createElement("a");
  link.href = trailerLink;

  link.classList.add("movie-link")
  link.innerText = "Trailer link"; //link to something??
  cardBody.append(link);

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
