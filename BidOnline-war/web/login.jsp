<%-- 
    Document   : login
    Created on : Jan 30, 2016, 1:13:30 AM
    Author     : prabuddha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Login | Bid-Online</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">
        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->       

    </head><!--/head-->

    <body>
        <header id="header"><!--header-->
            <div class="header_top"><!--header_top-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="contactinfo">
                                <ul class="nav nav-pills">
                                    <li><a href=""><i class="fa fa-phone"></i> +2 95 01 88 821</a></li>
                                    <li><a href=""><i class="fa fa-envelope"></i> info@domain.com</a></li>
                                </ul>
                            </div>
                        </div>

                    </div>
                </div>
            </div><!--/header_top-->

        </header><!--/header-->

        <section id="form"><!--form-->
            <div class="container">
                <div class="row">
                    <div class="col-sm-4 col-sm-offset-1">
                        <div class="login-form"><!--login form-->
                            <h2>${requestScope["MESSAGE_SUCCESS"]}</h2>
                            <h2>${requestScope["MESSAGE_ERROR"]}</h2>
                            <h2>Login to your account</h2>
                            <form action="Login" method="POST">
                                <input type="email" name="email" placeholder="Email Address" />
                                <input type="password" name="password" placeholder="Password" />
                                <button type="submit" class="btn btn-default">Login</button>
                            </form>
                        </div><!--/login form-->
                    </div>
                    <div class="col-sm-1">
                        <h2 class="or">OR</h2>
                    </div>
                    <div class="col-sm-4">
                        <div class="signup-form"><!--sign up form-->
                            <h2>New User Signup!</h2>
                            
                            <form action="Registration" method="POST">
                                <input type="text" name="first_name" placeholder="First Name"/>
                                <input type="text" name="middle_name" placeholder="Middle Name"/>
                                <input type="text" name="last_name" placeholder="Last Name"/>
                                <input type="text" name="contact_no" placeholder="Phone Number"/>
                                <input type="text" name="email" placeholder="Email Address"/>
                                <input type="text" name="address" placeholder="Address"/>
                                <input name="password" type="password" placeholder="Password"/>
                                <button type="submit" class="btn btn-default">Signup</button>
                            </form>

                        </div><!--/sign up form-->
                    </div>
                </div>
            </div>
        </section><!--/form-->


         
        <jsp:include page="footer.jsp" />


        <script src="js/jquery.js"></script>
        <script src="js/price-range.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>
