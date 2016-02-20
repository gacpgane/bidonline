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
                <div class="step-one">
                    <h2 class="heading">Create a Bid</h2>
                </div>
                <div class="shopper-informations">
                    
                    <div class="row">

                        <div class="col-sm-4">


                            <div class="create-bid-form">
                                <p>Item Information</p>
                                <form>
                                    <input id="itemName" type="text" placeholder="Name">
                                    <textarea id="itemDescription" placeholder="Details about your Bid (Bid Description)" rows="8"></textarea>
                                     
                                    <input id="marketPrice" type="text" placeholder="Market Price (SGD)">
                                    <input id="bidPrice" type="text" placeholder="Starting Bid Price (SGD)">
                                    <select id="category">
                                        <c:forEach var="category" items="${applicationScope.categoryList}">
                                            <option value="${category.code}">${category.name}</option>
                                        </c:forEach>
                                    </select>
                                    <select id="subCategory">
                                    </select>
                                    <p>File Upload</p>
                                    <input type="file" name="datafile" placeholder="File upload" />   
                                    
                                    <div id="upload" style="display:none;">Uploading..</div>



                                </form>
                            </div>


                        </div>
                        <div class="col-sm-4">
                            <div class="create-bid-form">
                                <form>
                                    <p>Bid Start Date/Time</p>
                                    <div id="datetimepicker1">
                                        <input id="startDate" type="text" placeholder="Bid Start Date"></input>
                                        <span class="add-on">
                                            <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                                        </span>
                                    </div>
                                    <p>Bid End Date/Time</p>
                                    <div id="datetimepicker2">
                                        <input id="endDate" type="text" placeholder="Bid End Date"></input>
                                        <span class="add-on">
                                            <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                                        </span>
                                    </div>
                                    <button type="submit" class="btn btn-default" id="createBid">Create</button>
                                </form>
                            </div>
                            <div>                                 

                            </div>
                        </div>
                        <div class="col-sm-4">
                            <div class="create-bid-form" id="itemPreview">
                                <p>Item Preview</p>
                                <div class="product-image-wrapper">
                                    <div class="single-products">
                                        <div class="productinfo text-center" id="itemSummary">
                                            <img id="itemImg" src="" alt="" />
                                              

                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>				
                </div>
            </div>
        </div>

    </section>

    <jsp:include page="footer.jsp" />

    <script type="text/javascript"
            src="js/bootstrap-datetimepicker.min.js">
    </script>
    <script type="text/javascript"
            src="js/bootstrap-datetimepicker.pt-BR.js">
    </script>
    <script src="js/jquery.ajax.fileupload.js"></script>
    
    <script type="text/javascript">
                $('#datetimepicker1').datetimepicker({
                    format: 'dd/MM/yyyy hh:mm:ss',
                    language: 'en'
                });
                $('#datetimepicker2').datetimepicker({
                    format: 'dd/MM/yyyy hh:mm:ss',
                    language: 'en'
                });
                $('input[type="file"]').ajaxfileupload({
                    'action': 'UploadFile',
                    'onComplete': function (response) {
                        var url=response.img.url;
                        alert(response.img.id);
                        $('#itemSummary').html("");
                        $('#itemSummary').append("<img id='itemImg' src='"+url+"' alt=''/>");
                        $("#itemSummary").append("<input type='hidden' id='imgId' value='"+response.img.id+"'></input>");
                        $('#itemPreview').show();
                        $('#upload').hide();
                        //$('#message').hide();
                        alert("File Uploaded Successfully!!");
                    },
                    'onStart': function () {
                        $('#upload').show();
                        //$('#message').hide();
                    }
                });





    </script>
    <script>
        $(document).ready(function () {
            var code = $('#category').val();
            var data = "code=" + code;
            $('#itemPreview').hide(); 
            //$('#message').hide();
            $.ajax({
                type: "GET",
                url: "CategoryServlet",
                data: data,
                dataType: "json",
                success: function (data, textStatus, jqXHR) {
                    if (data.success) {

                        $("#subCategory").html("");
                        for (i = 0; i < data.items.length; i++) {
                            item = data.items[i];
                            $("#subCategory").append("<option value='" + item.code + "'>" + item.name + "</option>");
                        }


                    } else {
                        $("#subCategory").append("<option value='-1'> --Select Sub Category -- </option>");
                    }
                }
            });
        });
        $('#category').change(function () {
            
            var code = $('#category').val();
            var data = "code=" + code;
             
            $.ajax({
                type: "GET",
                url: "CategoryServlet",
                data: data,
                dataType: "json",
                success: function (data, textStatus, jqXHR) {
                    if (data.success) {

                        $("#subCategory").html("");
                        for (i = 0; i < data.items.length; i++) {
                            item = data.items[i];
                            $("#subCategory").append("<option value='" + item.code + "'>" + item.name + "</option>");
                        }


                    } else {
                        $("#subCategory").append("<option value='-1'> --Select Sub Category -- </option>");
                    }
                }
            });
        });
        $('#createBid').click(function (e) {
            e.preventDefault();
            var name = $('#itemName').val();
            var description = $('#itemDescription').val();
            var marketPrice = $('#marketPrice').val();
            var bidPrice = $('#bidPrice').val();
            
            var code = $('#subCategory').val();
            var startDate = $('#startDate').val();
            var endDate = $('#endDate').val();
            var imgId=$('#imgId').val();
            var imgUrl=$('#itemImg').attr('src');
            var params={name,description,marketPrice,bidPrice,code,startDate,endDate,imgId,imgUrl};
            alert(imgId); 
            var data = jQuery.param(params);
            
            $.ajax({
                type: "POST",
                url: "ItemByCategory",
                data: data,
                dataType: "json",
                success: function (data, textStatus, jqXHR) {
                    if (data.success) {
                        //$('#successMessage').html("");
                        //$('#successMessage').append("<h2>Item Created Successfully!</h2>");
                        $('#itemSummary').html("");
                        $('#itemSummary').append("<img id='itemImg' src='"+data.item.imgUrl+"' alt='' />");
                        $('#itemSummary').append("<h2>"+data.item.price+" SGD</h2>");
                        $('#itemSummary').append("<h2>"+data.item.name+"</h2>");
                        alert("Item created Successfully!!");
                    } else {
                        alert("Item creation unsuccessfull!");
                    }
                }
            });
        });
    </script>     
</body>
</html>
