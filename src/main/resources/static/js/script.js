
function showSlider(idCarousel, number, classToHide) {
  $('#' + idCarousel).show();
  switch (number) {
    case 1 : $('#carItem1').addClass('active');
      break;
    case 2 : $('#carItem2').addClass('active');
      break;
    case 3 : $('#carItem3').addClass('active');
      break;
    case 4 : $('#carItem4').addClass('active');
      break;
    case 5: $('#carItem5').addClass('active');
      break;
    case 6 : $('#carItem6').addClass('active');
      break;
  }
  $('.' + classToHide).remove();
}