<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>

	#container {
	  	width: 100%;
	  	height: 100%;
	 	text-align: center;
	 	position: relative;
	  	z-index: 1;
	}

	#container::after {
		width: 100%;
		height: 100%;
		content: "";
		background: url("upload/bg_image.jpg");
		position: absolute;
  		top: 0;
  		left: 0;
  		z-index: -1;
	}

</style>
    
<header class="bg-dark py-5" id="container">
		    <div class="text-center text-black" >
		<div class="container px-4 px-lg-5 my-5">
		        <h1 class="display-4 fw-bolder">PetMart</h1>
		        <p class="lead fw-normal text-black-50 mb-0" >for your doggy, spend your money</p>
		    </div>
		</div>
</header>