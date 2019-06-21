var x = new XMLHttpRequest();
x.open('GET', "http://demo2469824.mockable.io/foods", false);
x.setRequestHeader("Content-Type", "text/xml");
x.send(null);
var TheDocument = x.responseXML;


// Place the root node in an element.

// Retrieve each customer in turn.



var foods = TheDocument.childNodes[0];
var FoodKind = document.getElementsByClassName('foodKind')[0];

var paste = '';
for (var i = 0; i < 4; i++) {

   var food = foods.children[i];
   // Access each of the data values.


   var ImgUrl = food.getElementsByTagName("imgUrl")[0];
   var ImgUrlValue = ImgUrl.childNodes[0].nodeValue;



   var Count = food.getElementsByTagName("count")[0];
   var countValue = Count.childNodes[0].nodeValue;




   var Name = food.getElementsByTagName("name")[0];
   var NameVal = Name.childNodes[0].nodeValue;
   var nameValue = "";

   switch (NameVal) {
      case "pizza":
         NameValue = "پیتزا";
         break;
      case "kebab":
         NameValue = "کباب";
         break;
      case "sandwich":
         NameValue = "ساندویچ";
         break;
      case "burger":
         NameValue = "برگر";
         break;

   }



   paste += '<div class="foodImg"><img class="foodImagecontent" src=' + ImgUrlValue + ' alt=' + NameVal + '><div class="bottom-right">' + NameValue + '<br> <div class="first"></div>' + countValue + 'رستوران فعال</div></div></div>';


}

$(paste).appendTo(".foodImage");







var kindFoodChs = document.getElementsByClassName('kindFoodChs')[0];

var KindPaste = '';
for (var i = 11; i >=0; i--) {

   var food = foods.children[i];
   // Access each of the data values.



   var Count = food.getElementsByTagName("count")[0];
   var countValue = Count.childNodes[0].nodeValue;




   var Name = food.getElementsByTagName("name")[0];
   var NameValx = Name.childNodes[0].nodeValue;
   

   switch (NameValx) {
      case "pizza":
         NameValx = "پیتزا";
         break;
      case "kebab":
         NameValx = "کباب";
         break;
      case "sandwich":
         NameValx = "ساندویچ";
         break;
      case "burger":
         NameValx = "برگر";
         break;
      case "salad":
         NameValx = "سالاد";
         break;
      case "iranian":
         NameValx = "ایرانی";
         break;
      case "pasta":
         NameValx = "پاسنا";
         break;
      case "fish":
         NameValx = "ماهی";
         break;
      case "breakfast":
         NameValx = "صبحانه";
         break;
      case "juice":
         NameValx = "آبمیوه";
         break;
      case "soup":
         NameValx = "سوپ";
         break;
         case "steak":
            NameValx = "استیک";
            break;



   }



   KindPaste += '<a href="#">' + NameValx + '</a>';

}

$(KindPaste).appendTo(".kindFoodChs");
