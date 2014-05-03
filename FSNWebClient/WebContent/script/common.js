var active_color = '#000'; // Colour of user provided text
var inactive_color = '#595858'; // Colour of default text

$(document).ready(
function() {
	// alert('new page will be loaded : ' + $.cookie('mnp_menu'));

	$('.notify').jConfirmAction();
	$('.submitbtn').click(function() {
		$(this).parents('form').submit();
		return false;
	});

	initMenu();

	$('.tooltip').Tooltip({
		showURL : false,
		showBody : " - ",
		left : 0,
		track : false
	});

	//alert($.cookie('mnp_menu'));

	var menuItem= ${mnpmenu};
	
//	alert('menuitem : ' + menuItem[0]);
	
	if (menuItem[0] != '') {
//		$('#' + $.cookie('mnp_menu')).find('ul').css({
//			'display' : 'block'
//		});
		$('#' + menuItem[0]).find('ul').css({
			'display' : 'block'
		});
	} 
	
	performMsisdnDefaultValue();


//	$('#menu li').click(function() {
//		$.cookie('mnp_menu', null);
//		$.cookie('mnp_menu', $(this).attr('id'));
//	});

});


function initMenu() {
	$('#menu ul').hide();
	$('#menu li a').click(function() {
		$(this).next().slideToggle('normal');
	});
}


 
//MNP-3244
function performMsisdnDefaultValue() {
  $("input.defaultValue").css("color", inactive_color);
  var default_values = new Array();
  $("input.defaultValue").focus(function() {
    if (!default_values[this.id]) {
      default_values[this.id] = this.value;
    }
    if (this.value == default_values[this.id]) {
      this.value = '';
      this.style.color = active_color;
    }
    $(this).blur(function() {
      if (this.value == '') {
        this.style.color = inactive_color;
        this.value = default_values[this.id];
      }
    });
  });
}
