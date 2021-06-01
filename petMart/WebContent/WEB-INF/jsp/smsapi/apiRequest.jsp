<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


/*
https://code.google.com/archive/p/crypto-js/
https://storage.googleapis.com/google-code-archive-downloads/v2/code.google.com/crypto-js/CryptoJS%20v3.1.2.zip
*/

/*
CryptoJS v3.1.2
code.google.com/p/crypto-js
(c) 2009-2013 by Jeff Mott. All rights reserved.
code.google.com/p/crypto-js/wiki/License
*/
<script type="text/javascript" src="./CryptoJS/rollups/hmac-sha256.js"></script>
<script type="text/javascript" src="./CryptoJS/components/enc-base64.js"></script>

function makeSignature() {
	var space = " ";				// one space
	var newLine = "\n";				// new line
	var method = "GET";				// method
	var url = "/sms/v2/services/$serviceId/messages";	// url (include query string)
	var timestamp = "{DateTime.now()}";			// current timestamp (epoch)
	var accessKey = "{accessKey}";			// access key id (from portal or Sub Account)
	var secretKey = "{secretKey}";			// secret key (from portal or Sub Account)

	var hmac = CryptoJS.algo.HMAC.create(CryptoJS.algo.SHA256, secretKey);
	hmac.update(method);
	hmac.update(space);
	hmac.update(url);
	hmac.update(newLine);
	hmac.update(timestamp);
	hmac.update(newLine);
	hmac.update(accessKey);

	var hash = hmac.finalize();

	return hash.toString(CryptoJS.enc.Base64);
}
