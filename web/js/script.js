// init Isotope
var $grid = $('.collection-list').isotope({
  // options
});
// filter items on button click
$('.filter-button-group').on('click', 'button', function () {
  var filterValue = $(this).attr('data-filter');
  resetFilterBtns();
  $(this).addClass('active-filter-btn');
  $grid.isotope({ filter: filterValue });
});

var filterBtns = $('.filter-button-group').find('button');
function resetFilterBtns() {
  filterBtns.each(function () {
    $(this).removeClass('active-filter-btn');
  });
}


// Login register
//document.addEventListener("DOMContentLoaded", () => {
//  const loginForm = document.querySelector("#login");
//  const registerForm = document.querySelector("#register");
//  document.querySelector("#buttonRegister").addEventListener("click", e => {
//    e.preventDefault();
//    loginForm.classList.add("form-hidden");
//    registerForm.classList.remove("form-hidden");
//  });
//
//  document.querySelector("#buttonLogin").addEventListener("click", e => {    
//    e.preventDefault();
//    loginForm.classList.remove("form-hidden");
//    registerForm.classList.add("form-hidden");
//  });
//});


// Product change img
//document.addEventListener("DOMContentLoaded", () => {
//  const mainImg = document.getElementById('main-img-product');
//  const smallImg = document.getElementsByClassName('small-img');
//
//  smallImg[0].addEventListener("click", () => {
//    mainImg.src = smallImg[0].src;
//  });
//
//  smallImg[1].addEventListener("click", () => {
//    mainImg.src = smallImg[1].src;
//  });
//
//  smallImg[2].addEventListener("click", () => {
//    mainImg.src = smallImg[2].src;
//  });
//
//  smallImg[3].addEventListener("click", () => {
//    mainImg.src = smallImg[3].src;
//  });
//});


