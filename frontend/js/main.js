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

  //Test
  let movietrailerlink = movie.trailerLink;

  let imagesrc = movie.imagesrc;

  let image = document.createElement("img");
  image.src = imagesrc;
  image.classList.add("card-img-top");
  image.alt = "img/FrenchFriday.png";
  movieElement.append(image);

  let cardBody = document.createElement("div");
  cardBody.classList.add("card-body");
  let cardHeader = document.createElement("h5");
  cardHeader.textContent = movie.title; //title goes here;
  cardHeader.classList.add("movie-title");
  cardBody.append(cardHeader);

  let cardParagraph = document.createElement("p");
  cardParagraph.textContent = "INSERT MOVIE ATTRIBUT DESCRIPTION HERE";
  cardParagraph.classList.add("card-text");
  cardBody.append(cardParagraph);

  const cardButton = document.createElement("a");
  cardButton.textContent = "Læs mere";
  cardButton.href = "film?id=" + movie.id;
  cardButton.classList.add("btn");
  cardButton.classList.add("btn-outline-primary");
  cardBody.append(cardButton);
  cardButton.addEventListener("click", showCardModal);

  const cardButtonLink = document.createElement("button");
  cardButtonLink.textContent = "Se trailer";
  cardButtonLink.id = "trailerButton";
  cardButtonLink.classList.add("btn");
  cardButtonLink.classList.add("btn-outline-secondary");
  cardButtonLink.classList.add("mx-2");
  cardButtonLink.setAttribute("data-bs-toggle", "modal");
  cardButtonLink.setAttribute("data-bs-target", "#modalYoutube");

  cardBody.append(cardButtonLink);

  initModalBox();

  movieElement.append(cardBody);

  function showCardModal() {
  }

  function initModalBox() {
    const modalDiv = document.createElement("div");
    modalDiv.id = "modalYoutube";
    modalDiv.classList.add("modal");
    modalDiv.classList.add("fade");
    modalDiv.setAttribute("aria-labelledby", "modalYoutube");
    document.body.append(modalDiv);

    const modalDialog = document.createElement("div");
    modalDialog.classList.add("modal-dialog");
    modalDialog.classList.add("modal-dialog-centered");
    modalDialog.classList.add("modal-lg");
    modalDiv.append(modalDialog);

    const modalContent = document.createElement("div");
    modalContent.classList.add("modal-content");
    modalDialog.append(modalContent);

    const modalHeader = document.createElement("div");
    modalHeader.classList.add("modal-header");
    modalContent.append(modalHeader);

    const modalTitle = document.createElement("h5");
    modalTitle.classList.add("modal-title");
    modalTitle.textContent = movie.title;
    modalHeader.append(modalTitle);

    const modalBody = document.createElement("div");
    modalBody.classList.add("modal-body");
    modalContent.append(modalBody);

    /*
    let movieLink = document.createElement("iframe");
    movieLink.src = movietrailerlink;
    console.log("TEst==" + movietrailerlink)
    movieLink.classList.add("trailerButton");
    movieElement.append(movieLink);
    */

    //"https://www.youtube.com/embed/y40LA-5sK4o"

    const youtubeIframe = document.createElement("iframe");
    youtubeIframe.setAttribute("src", movietrailerlink);
    youtubeIframe.setAttribute("width", "100%");
    youtubeIframe.setAttribute("height", "400px");
    youtubeIframe.setAttribute("title", movie.title +" trailer");
    youtubeIframe.setAttribute("frameborder", "0");
    youtubeIframe.setAttribute("allow", "accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture");
    youtubeIframe.setAttribute("allowfullscreen", "");
    modalBody.append(youtubeIframe);


    const modalCloseButton = document.createElement("button");
    modalCloseButton.classList.add("btn-close");
    modalCloseButton.setAttribute("data-bs-dismiss", "modal");
    modalHeader.append(modalCloseButton);
  }

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

