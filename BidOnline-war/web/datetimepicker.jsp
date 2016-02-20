<html>
	<head>
             <jsp:include page="banner.jsp" />
		<link rel="stylesheet" href="css/flipclock.css">

		<script src="js/jquery.js"></script>

		<script src="js/flipclock.js"></script>
	</head>
	<body>
	<div class="clock" style="margin:2em;"></div>
	<div class="message"></div>

	<script type="text/javascript">
		var clock;
		
		$(document).ready(function() {
			var clock;

			clock = $('.clock').FlipClock({
		        clockFace: 'DailyCounter',
		        autoStart: false,
		        callbacks: {
		        	stop: function() {
		        		$('.message').html('The clock has stopped!')
		        	}
		        }
		    });
				    
		    clock.setTime(220880);
		    clock.setCountdown(true);
		    clock.start();

		});
	</script>
	
	</body>
</html>