<%-- 
    Document   : index
    Created on : Jan 29, 2016, 11:47:23 PM
    Author     : prabuddha
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="header.jsp" />

    <body>

        <jsp:include page="banner.jsp" />


        <section>



            <div class="container">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="left-sidebar">
                            <h2>Category</h2>
                            <div class="panel-group category-products" id="accordian"><!--category-productsr-->
                                <c:forEach var="category" items="${applicationScope.categoryList}" varStatus="count">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordian" href="#${category.code}">
                                                <span class="badge pull-right"><i class="fa fa-plus"></i></span>
                                                    <c:out value="${category.name}"/>
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="${category.code}" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <ul>
                                                <c:forEach var="subCategory" items="${category.subCategories}">
                                                    <li><a class="subcategory" href="#${subCategory.code}"><c:out value="${subCategory.name}"/></a></li>
                                                    </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </c:forEach>
                                <a href='#' class='btn btn-default add-to-cart'></a>
                            </div><!--/category-products-->





                        </div>
                    </div>

                    <div class="col-sm-9 padding-right">
                        <div class="features_items" id="live_auctions"><!--features_items-->
                        </div><!--features_items-->
                        <div class="clock" style="margin:2em;"></div>
                        <div class="message"></div>
                        <div class="recommended_items"><!--recommended_items-->
                            <h2 class="title text-center">COMPLETED AUCTIONS</h2>

                            <div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
                                <div class="carousel-inner">
                                    <div class="item active">	
                                        <div class="col-sm-4">
                                            <div class="product-image-wrapper">
                                                <div class="single-products">
                                                    <div class="productinfo text-center">
                                                        <img src="images/home/recommend1.jpg" alt="" />
                                                        <h2>$50</h2>
                                                        <p>Congratulations to name1</p>

                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="product-image-wrapper">
                                                <div class="single-products">
                                                    <div class="productinfo text-center">
                                                        <img src="images/home/recommend2.jpg" alt="" />
                                                        <h2>$56</h2>
                                                        <p>Congratulations to name2</p>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="product-image-wrapper">
                                                <div class="single-products">
                                                    <div class="productinfo text-center">
                                                        <img src="images/home/recommend3.jpg" alt="" />
                                                        <h2>$56</h2>
                                                        <p>Congratulations to name3</p>

                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="item">	
                                        <div class="col-sm-4">
                                            <div class="product-image-wrapper">
                                                <div class="single-products">
                                                    <div class="productinfo text-center">
                                                        <img src="images/home/recommend1.jpg" alt="" />
                                                        <h2>$56</h2>
                                                        <p>Congratulations to name3</p>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="product-image-wrapper">
                                                <div class="single-products">
                                                    <div class="productinfo text-center">
                                                        <img src="images/home/recommend2.jpg" alt="" />
                                                        <h2>$56</h2>
                                                        <p>Congratulations to name4</p>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-4">
                                            <div class="product-image-wrapper">
                                                <div class="single-products">
                                                    <div class="productinfo text-center">
                                                        <img src="images/home/recommend3.jpg" alt="" />
                                                        <h2>$56</h2>
                                                        <p>Congratulations to name5</p>

                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <a class="left recommended-item-control" href="#recommended-item-carousel" data-slide="prev">
                                    <i class="fa fa-angle-left"></i>
                                </a>
                                <a class="right recommended-item-control" href="#recommended-item-carousel" data-slide="next">
                                    <i class="fa fa-angle-right"></i>
                                </a>			
                            </div>
                        </div><!--/recommended_items-->

                    </div>
                </div>
            </div>
        </section>

        <jsp:include page="footer.jsp" />

        <script>

            $(document).ready(function () {
                dataString = "page=1";
                $.ajax({
                    type: "POST",
                    url: "BidItemServlet",
                    data: dataString,
                    dataType: "json",
                    success: function (data, textStatus, jqXHR) {
                        if (data.success) {
                            var item;
                            $("#live_auctions").html("");
                            $("#live_auctions").append("<h2 class='title text-center'>LIVE AUCTIONS</h2>");

                            for (i = 0; i < data.items.length; i++) {
                                item = data.items[i];
                                if (item.timeInSeconds > 0) {
                                    $("#live_auctions").append("<div class='col-sm-4'><div class='product-image-wrapper'><div class='single-products'><div class='productinfo text-center'><img src='" + item.imgUrl + "' alt=''/>" + "<h2 id='price_"+item.id+"'>" + item.price + "</h2>" + "<p>" + item.name + "</p>" + "<div class='timer' data-seconds-left='" + item.timeInSeconds + "'> </div>" + "<a href='#' class='btn btn-default add-to-cart'><i class='fa fa-shopping-cart'></i>BID</a>" + "</div><div class='product-overlay'><div class='overlay-content'>" + "<h2 id='overlay_price_"+item.id+"'>" + item.price + "</h2>" + "<p>" + item.name + "</p>" + "<div class='timer' data-seconds-left='" + item.timeInSeconds + "'> </div>" + " <p>My Bid (SGD):</p><input id='bidvalue_" + item.id + "' class='bidValue' type='text' value=''></input>" + "<a href='#' class='btn btn-default add-to-cart' id='bid_" + item.id + "'><i class='fa fa-shopping-cart'></i>BID</a></div></div></div></div>");
                                }
                            }

                            $('.timer').startTimer({
                                onComplete: function () {
                                    $(this).append("<p>Expired</p>");
                                    console.log('Complete');
                                }
                            });


                        } else {
                            $("#live_auctions").html("<div><b>Item Invalid!</b></div>");
                        }
                    }
                });

            });

        </script>   
        <script>
            $('.subcategory').click(function () {

                dataString = $(this).attr("href");
                dataString = dataString.substr(1);

                $.ajax({
                    type: "POST",
                    url: "ItemByCategory",
                    data: "code=" + dataString,
                    dataType: "json",
                    success: function (data, textStatus, jqXHR) {

                        if (data.success) {
                            var item;
                            $("#live_auctions").html("");
                            $("#live_auctions").append("<h2 class='title text-center'>LIVE AUCTIONS</h2>");

                            for (i = 0; i < data.items.length; i++) {
                                item = data.items[i];
                                if (item.timeInSeconds > 0) {
                                    $("#live_auctions").append("<div class='col-sm-4'><div class='product-image-wrapper'><div class='single-products'><div class='productinfo text-center'><img src='" + item.imgUrl + "' alt=''/>" + "<h2>" + item.price + "</h2>" + "<p>" + item.name + "</p>" + "<div class='timer' data-seconds-left='" + item.timeInSeconds + "'> </div>" + "<a href='#' class='btn btn-default add-to-cart'><i class='fa fa-shopping-cart'></i>BID</a>" + "</div><div class='product-overlay'><div class='overlay-content'>" + "<h2>" + item.price + "</h2>" + "<p>" + item.name + "</p>" + "<div class='timer' data-seconds-left='" + item.timeInSeconds + "'> </div>" + " <p>My Bid (SGD):</p><input class='bidValue' type='text' value='' size='8'/>" + "<a href='#' class='btn btn-default add-to-cart'><i class='fa fa-shopping-cart'></i>BID</a></div></div></div></div>");
                                }
                            }

                            if (data.items.length == 0) {
                                $("#live_auctions").append("<p>No Items available under this category.</p>");
                            }

                            $('.timer').startTimer({
                                onComplete: function () {
                                    $(this).append("<p>Expired</p>")
                                    console.log('Complete');
                                }
                            });


                        } else {
                            $("#live_auctions").html("<div><b>Item Invalid!</b></div>");
                        }

                    }
                });

            });
        </script>
        <script>

            $(document).on("click", ".btn.btn-default.add-to-cart", function (e) {
                var bid_id = $(this).attr("id");
                var itemId=bid_id.substr(4);
                var bidValue=$("#bidvalue_"+itemId).val();
                var params={bidValue,itemId};
                var data = jQuery.param(params);
                $.ajax({
                    type: "POST",
                    url: "CreateBidServlet",
                    data: data,
                    dataType: "json",
                    success: function (data, textStatus, jqXHR) {

                        if (data.success) {
                            var item=data.bidItem;
                            $("#overlay_price_"+itemId).text(item.value);
                            $("#price_"+itemId).text(item.value);
                            alert("We have successfully placed your bid!");
                        } else {
                            alert("We are unable to place your bid!");
                        }
                    }
                });
            });
        </script>
        <style>
            .days {
                float: left;
                margin-right: 4px;
            }
            .hours {

                float:left ;
                padding-left: 100px;

            }
            .minutes {
                float: left;
            }
            .seconds {
                float: left;
            }
            .clearDiv {
                clear: both;
            }
        </style>



    </body>
</html>
