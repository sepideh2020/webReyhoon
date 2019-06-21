var getJSON = function(url, callback) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.responseType = 'json';
    xhr.onload = function() {
      var status = xhr.status;
      if (status === 200) {
        callback(null, xhr.response);
      } else {
        callback(status, xhr.response);
      }
    };
    xhr.send();
};
var url = "http://demo2469824.mockable.io/best-restaurants";    
var dataToProc = "";

getJSON(url,function(err, data) {
  if (err !== null) {
    alert('Something went wsrong: ' + err);


  } else {

    showBestRes(data);
    showOthers(data);
    }
});

function getStars(rating) {
   
  // Round to nearest half
  rating = Math.round(rating * 2) / 2;
  let output=[];
 
 

  // Append all the filled whole stars
  for (var i = rating; i >= 1; i--)
    output.push('<i class="fa fa-star" aria-hidden="true" style="color: gold;"></i>&nbsp;');

  // If there is a half a star, append it
  if (i == .5) output.push('<i class="fa fa-star-half-o" aria-hidden="true" style="color: gold;"></i>&nbsp;');

  // Fill the empty stars
  for (let i = (5 - rating); i >= 1; i--)
    output.push('<i class="fa fa-star-o" aria-hidden="true" style="color: gold;"></i>&nbsp;');

  return output.join('');
   
}


function showBestRes(data){

  var toPrint= "";
  for (i=0 ; i < 3 ; i++){
    var $imgSrc= data.restaurants[i].imgUrl;
    var $resName=data.restaurants[i].name;
    var $pRate=data.restaurants[i].numOfRates;

    
    var $address=data.restaurants[i].address;
    var $resrate=data.restaurants[i].rate;  
    var $star=data.restaurants[i].rate;
  
    
    var $foodItem="";
     for( var j=0;j<4;j++){
       switch(data.restaurants[i].foods[j])
       {
          case "pizza":
            $foodItem=$foodItem +"پیتزا"+ '&#9679';
            break;
          case "fastfood":
            $foodItem=$foodItem +"فست فود"+ '&#9679';
            break;
         case "sandwich":
            $foodItem=$foodItem +"ساندویج"+ '&#9679';
            break;
         case "burger":
            $foodItem=$foodItem +"برگر";
            break;

       }
     }

     
    var forPrint = `<a href="`+ $imgSrc +`" class="bestResMonthSq">
    <img class="bestRestMonthimg" src="`+ $imgSrc +`" alt="">
    <div class="bestResMonthName">`+ $resName +`</div>
    
    <span class="grayrating">------</span>
    <span class="numberrating">`+ $resrate +`</span>
    <span class="star100">`+ getStars($star) +`</span>
    <span class="prate">`+'('+$pRate +')'+` </span>
    <span class="grayrating">------</span>
    <div class="foodtype">`+ $foodItem +` </div>
    <div class="address">`+ $address +`</div>
    <br>
    <div><button type="button" class="pinkbutton">ثبت سفارش</button></div>
  </a>`;

    toPrint = toPrint.concat(forPrint); 

  }
 
  document.getElementById("demo").innerHTML = toPrint;
  
}

function showOthers(data){
  var toPrint= "";
  for (i=3 ; i < 15 ; i++){
    var $imgSrc= data.restaurants[i].imgUrl;
    var $resName=data.restaurants[i].name;
   
    var forPrint = `<a  class="reslink" href="#" style="    background-size: 80px;background-image: url(`+ $imgSrc +`)">`+ $resName +`</a>
    `;

    toPrint = toPrint.concat(forPrint); 

  }
 
  document.getElementById("otherRes").innerHTML = toPrint;

  
}

