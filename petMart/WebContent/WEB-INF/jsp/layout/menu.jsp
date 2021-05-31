<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="homePage.do">
                <img src="image/logo1.png" width="100" height="70">
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="homePage.do">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="noticeListPaging.do">Notice</a></li>
                        <li class="nav-item"><a class="nav-link" href="bulletinListPaging.do">FreeBoard</a></li>
                        <c:if test="${id eq null}">
                        	<li class="nav-item"><a class="nav-link" href="memberLoginForm.do">Login</a></li>
                        	<li class="nav-item"><a class="nav-link" href="memberJoinForm.do">Sign In</a></li>
                        </c:if>
                        <c:if test="${id ne null}">
                        	<li class="nav-item"><a class="nav-link" href="memberLoginOut.do">Logout</a></li>
                        </c:if>
                 
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="productListPaging.do">All Products</a></li>
                                <li><hr class="dropdown-divider" /></li> 
                                <li><a class="dropdown-item" href="#!">Popular Items</a></li>
                                <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
                                <!--  -->
                            </ul>
                            
                        </li>
                        </ul>
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   
                   
	                    <form action="searchList.do" method="post">
	                		<input class="form-control" name="keyword" type="text" size="15" placeholder="Search" required="required">
	                    </form>
	                   <form class="d-flex" action="cartList.do?">
	                   		<input type="hidden" name="uid" value="${id }">
	                       <button class="btn btn-outline-dark" type="submit">
	                           <i class="bi-cart-fill me-1"></i>
	                           <c:if test="${id ne null }">
	                           		${id }'s Cart
	                           </c:if>
	                           <c:if test="${id eq null }">
	                           		Guest Cart
	                           </c:if>
	                           <span class="badge bg-dark text-white ms-1 rounded-pill">${cartCnt }</span>
	                       </button>
	                   </form>
                    
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                     <c:if test="${id ne null}">
                        	<li class="nav-item"><a class="nav-link" href="memberCancelForm.do">Membership Cancellation</a></li>
                        </c:if>
                     </ul>

                </div>
            </div>
        </nav>