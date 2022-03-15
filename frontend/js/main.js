async function loadHtmlTemplate(link, elementid) {
  let result = await fetch(link)
  let text = await result.text();

  let domparser = new DOMParser();
  let html = domparser.parseFromString(text, "text/html");

  if((f = html.body.querySelector('div')) !== null) {
    document.getElementById(elementid).append(f);
  }
}

if(document.readyState === 'loading'){
  window.addEventListener('DOMContentLoaded', () => {
    buildPage();
  });
}else{
  buildPage();
}

async function buildPage(){
  loadHtmlTemplate("./html/navbar.html", 'navbar');
  loadHtmlTemplate("./html/carusel.html", 'carusel');
  await loadHtmlTemplate("./html/movieContainer.html", 'moviecontainer');
  makeMovieList();
}

let movieNumber = 1;

function buildMovie(){
  let movieContainer = document.createElement("div");
  movieContainer.classList.add("col");

  let movie = document.createElement("div");
  movie.id = "movie" + movieNumber;
  movie.classList.add("card");
  movie.classList.add("movie-width");

  //image source goes here
  let imagesrc = "https://m.media-amazon.com/images/M/MV5BNTBjZTBlN2YtOWQzZC00YTAzLWFiOWUtYzRiZWRmNjA5YWFmXkEyXkFqcGdeQXVyMTA0NTIyOTQ@._V1_.jpg";
  let image = document.createElement("img");
  image.src = imagesrc;
  image.classList.add("card-img-top");
  image.alt="img/FrenchFriday.png";
  movie.append(image);

  let cardBody = document.createElement("div");
  cardBody.classList.add("card-body");
  let cardHeader = document.createElement("h5");
  cardHeader.innerText = "Movie" + movieNumber; //title goes here
  cardBody.append(cardHeader);

  let cardtext = document.createElement("p");
  cardtext.innerText = "Lorem ipsum dolor sit amet consectetur adipisicing elit.Aperiam assumenda consequatur consequuntur"; //description goes here
  cardtext.classList.add("card-text");
  cardBody.append(cardtext);
  movie.append(cardBody);

  let cardList = document.createElement("ul"); //screenings?
  cardList.classList.add("list-group");
  cardList.classList.add("list-group-flush");

  for(let i = 0; i < 3; i++){ //change to for each screening?
    let cardElement = document.createElement("li");
    cardElement.classList.add("list-group-item");
    cardElement.innerText = "Screening " + i;
    cardList.append(cardElement);
  }
  movie.append(cardList);

  cardBody = document.createElement("div");
  let link = document.createElement("a");
  link.href="#";
  link.innerText = "Card Link"; //link to something??
  cardBody.append(link);

  movie.append(cardBody);

  movieContainer.append(movie);
  return movieContainer;

}

function makeMovieList(){
  let movieContainer = document.getElementById("movies");
  movieContainer.append(buildMovie());
  movieContainer.append(buildMovie());
  movieContainer.append(buildMovie());
  movieContainer.append(buildMovie());
  movieContainer.append(buildMovie());
  movieContainer.append(buildMovie());
}
