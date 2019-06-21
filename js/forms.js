var currentTab = 0;
showTab();

function showTab() {
  if (currentTab == 0)//login
  {
//    $("#login").removeClass("hide");
//    $("#register").addClass("hide");
//    $("#btnTabLogin").addClass("actived");
//    $("#btnTabRegister").removeClass("actived");
    
   document.getElementById("login").classList.remove("hide");
   document.getElementById("register").classList.add("hide");
   document.getElementById("btnTabLogin").classList.add("actived");
   document.getElementById("btnTabRegister").classList.remove("actived");
  }
  else//register
  {
     document.getElementById("login").classList.add("hide");
     document.getElementById("register").classList.remove("hide");
     document.getElementById("btnTabLogin").classList.remove("actived");
     document.getElementById("btnTabRegister").classList.add("actived");
    
    
    
  }
}

function regclick()
{
  currentTab = 1;
  showTab();
}
function loginclick()
{
  currentTab = 0;
  showTab();
}
