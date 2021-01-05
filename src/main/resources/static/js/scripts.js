(function($) {
    "use strict"; // Start of use strict

    // Smooth scrolling using jQuery easing
    $('a.js-scroll-trigger[href*="#"]:not([href="#"])').click(function() {
        if (
            location.pathname.replace(/^\//, "") ==
            this.pathname.replace(/^\//, "") &&
            location.hostname == this.hostname
        ) {
            var target = $(this.hash);
            target = target.length ?
                target :
                $("[name=" + this.hash.slice(1) + "]");
            if (target.length) {
                $("html, body").animate({
                        scrollTop: target.offset().top - 72,
                    },
                    1000,
                    "easeInOutExpo"
                );
                return false;
            }
        }
    });

    // Closes responsive menu when a scroll trigger link is clicked
    $(".js-scroll-trigger").click(function() {
        $(".navbar-collapse").collapse("hide");
    });

    // Activate scrollspy to add active class to navbar items on scroll
    $("body").scrollspy({
        target: "#mainNav",
        offset: 74,
    });

    // Collapse Navbar
    var navbarCollapse = function() {
        if ($("#mainNav").offset().top > 100) {
            $("#mainNav").addClass("navbar-shrink");
        } else {
            $("#mainNav").removeClass("navbar-shrink");
        }
    };
    // Collapse now if page is not at top
    navbarCollapse();
    // Collapse the navbar when page is scrolled
    $(window).scroll(navbarCollapse);

    $('#meuModal').on('shown.bs.modal', function() {
        $('#meuInput').trigger('focus')
    });
    
    $(document).ready(function(){
 	   $("#resposta").find("input[type='radio']").click(function(){
 	      var idRadio = $(this).attr("id");
 	      var aux = idRadio.split("radio");
 	      
 	      if(aux[0] === "I") {
 	    	 var idSelect = "selectI" + aux[1];
 	    	 $('.disabilitarI').prop('disabled', true);
 	    	 $('.disabilitarI').prop('required', false);
 	    	 $('#' + idSelect).prop('disabled', false);
 	    	 $('.disabilitarI').prop('required', true);
 	    	 $('.trCorI').css('backgroundColor', '');
 	    	 $('#trI' + aux[1]).css('backgroundColor', '#7FFFD4');
 	      } else {
 	    	 var idSelect = "selectV" + aux[1]; 
 	    	 $('.disabilitarV').prop('disabled', true);
 	    	 $('.disabilitarV').prop('required', false);
 	    	 $('#' + idSelect).prop('disabled', false);
 	    	 $('.disabilitarV').prop('required', true);
 	    	 $('.trCorV').css('backgroundColor', '');
	    	 $('#trV' + aux[1]).css('backgroundColor', '#7FFFD4');
 	      }
 	      
 	      
 	      //alert(idSelect);
 	   });
 	});
    
   /* $('.disabilitarI').change(function (){
    	$('#assentoI').val($(this).val());
        alert($('#assentoI').val());
    });
    
    $('.disabilitarV').change(function (){
    	$('#assentoV').val($(this).val());
        alert($('#assentoV').val());
    });*/
    
})(jQuery);