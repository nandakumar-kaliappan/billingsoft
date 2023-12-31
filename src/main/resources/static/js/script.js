console.log("welcome (Fetch)");
fetch( "/api/v1/product")
.then(respose=>respose.json())
.then(data=>console.log(data));