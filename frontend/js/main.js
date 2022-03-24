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
  cardParagraph.innerText = "INSERT MOVIE ATTRIBUT DESCRIPTION HERE";
  cardParagraph.classList.add("card-text");
  cardBody.append(cardParagraph);

  const cardButton = document.createElement("a");
  cardButton.innerText = "Læs mere";
  cardButton.href = "#";
  cardButton.classList.add("btn");
  cardButton.classList.add("btn-outline-primary");
  cardBody.append(cardButton);
  cardButton.addEventListener("click", showCardModal );

  const cardButtonLink = document.createElement("button");
  cardButtonLink.innerText = "Se trailer";
  cardButtonLink.classList.add("btn");
  cardButtonLink.classList.add("btn-outline-secondary");
  cardButtonLink.classList.add("mx-2");
  cardButtonLink.setAttribute("data-bs-toggle", "modal");
  cardButtonLink.setAttribute("data-bs-target", "#exampleModal");
  cardBody.append(cardButtonLink);
  cardButtonLink.addEventListener("click", showYoutubeLink);

  movieElement.append(cardBody);

  let cardList = document.createElement("ul"); //screenings?
  cardList.classList.add("list-group");
  cardList.classList.add("list-group-flush");

  function showCardModal(){


  }

  function showYoutubeLink(){
    //const modalDivMain = document.createElement("");
    const modalDiv = document.createElement("div");
    modalDiv.id = "modalYoutube";
    modalDiv.classList.add("modal-dialog");
    modalDiv.classList.add("modal-dialog-centered");
    modalDiv.classList.add("modal-lg")
    cardBody.append(modalDiv);
    const modalContent = document.createElement("div");
    modalContent.classList.add("modal-content")
    modelContent.href("#")
    modalDiv.append(modalContent);
    const modalTitle = document.createElement("div");
    modalTitle.classList.add("modal-title");
    modalTitle.innerText = "Test";
    modalDiv.append(modalTitle);

    console.log("Vi er i modal div");
    modalDiv.id=movie.id;





    //modalDiv.id= movie.id;


  }



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
