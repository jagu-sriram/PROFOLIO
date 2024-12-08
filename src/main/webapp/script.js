const inputs = document.querySelectorAll(".input-field");
const toggle_btn = document.querySelectorAll(".toggle");
const main = document.querySelector("main");
const bullets = document.querySelectorAll(".bullets span");
const images = document.querySelectorAll(".image");

inputs.forEach((inp) => {
  inp.addEventListener("focus", () => {
    inp.classList.add("active");
  });
  inp.addEventListener("blur", () => {
    if (inp.value != "") return;
    inp.classList.remove("active");
  });
});

toggle_btn.forEach((btn) => {
  btn.addEventListener("click", () => {
    main.classList.toggle("sign-up-mode");
  });
});

function moveSlider() {
  let index = this.dataset.value;

  let currentImage = document.querySelector(`.img-${index}`);
  images.forEach((img) => img.classList.remove("show"));
  currentImage.classList.add("show");

  const textSlider = document.querySelector(".text-group");
  textSlider.style.transform = `translateY(${-(index - 1) * 2.2}rem)`;

  bullets.forEach((bull) => bull.classList.remove("active"));
  this.classList.add("active");
}

bullets.forEach((bullet) => {
  bullet.addEventListener("click", moveSlider);
});




/*===== EXPANDER MENU ON HOVER =====*/
const toggleMenuHover = (navbarId, bodyId) => {
  const navbar = document.getElementById(navbarId);
  const bodypadding = document.getElementById(bodyId);

  if (navbar) {
    navbar.addEventListener('mouseover', () => {
      navbar.classList.add('expander');
      bodypadding.classList.add('body-pd');
    });

    navbar.addEventListener('mouseout', () => {
      navbar.classList.remove('expander');
      bodypadding.classList.remove('body-pd');
    });
  }
};
toggleMenuHover('navbar', 'body-pd');

/*===== LINK ACTIVE ON HOVER =====*/
const linkColor = document.querySelectorAll('.nav__link');
function colorLinkHover() {
  linkColor.forEach(l => l.classList.remove('active'));
  this.classList.add('active');
}
linkColor.forEach(l => l.addEventListener('mouseover', colorLinkHover));


/*===== COLLAPSE MENU ON CLICK =====*/
const collapseLinks = document.querySelectorAll('.nav__link.collapse');

collapseLinks.forEach((link) => {
  const collapseMenu = link.querySelector('.collapse__menu');
  const rotateIcon = link.querySelector('.collapse__link');

  // Toggle menu on click
  link.addEventListener('click', (event) => {
    event.stopPropagation(); // Prevent event bubbling
    const isOpen = collapseMenu.classList.contains('showCollapse');

    // Close all other menus
    collapseLinks.forEach((otherLink) => {
      const otherMenu = otherLink.querySelector('.collapse__menu');
      const otherIcon = otherLink.querySelector('.collapse__link');
      if (otherMenu !== collapseMenu) {
        otherMenu.classList.remove('showCollapse');
        if (otherIcon) {
          otherIcon.classList.remove('rotate');
        }
      }
    });

    // Toggle current menu
    collapseMenu.classList.toggle('showCollapse', !isOpen);
    if (rotateIcon) {
      rotateIcon.classList.toggle('rotate', !isOpen);
    }
  });
});




var li_links = document.querySelectorAll(".links ul li");
var view_wraps = document.querySelectorAll(".view_wrap");
var list_view = document.querySelector(".list-view");
var grid_view = document.querySelector(".grid-view");

li_links.forEach(function(link){
  link.addEventListener("click", function(){
    li_links.forEach(function(link){
      link.classList.remove("active");
    })

    link.classList.add("active");

    var li_view = link.getAttribute("data-view");

    view_wraps.forEach(function(view){
      view.style.display = "none";
    })

    if(li_view == "list-view"){
      list_view.style.display = "block";
    }
    else{
      grid_view.style.display = "block";
    }
  })
})









initMultiStepForm();


function initMultiStepForm() {
    const progressNumber = document.querySelectorAll(".step").length;
    const slidePage = document.querySelector(".slide-page");
    const submitBtn = document.querySelector(".submit");
    const progressText = document.querySelectorAll(".step p");
    const progressCheck = document.querySelectorAll(".step .check");
    const bullet = document.querySelectorAll(".step .bullet");
    const pages = document.querySelectorAll(".page");
    const nextButtons = document.querySelectorAll(".next");
    const prevButtons = document.querySelectorAll(".prev");
    const stepsNumber = pages.length;

    if (progressNumber !== stepsNumber) {
        console.warn(
            "Error, number of steps in progress bar do not match number of pages"
        );
    }

    document.documentElement.style.setProperty("--stepNumber", stepsNumber);

    let current = 1;

    for (let i = 0; i < nextButtons.length; i++) {
        nextButtons[i].addEventListener("click", function (event) {
            event.preventDefault();

            inputsValid = validateInputs(this);
            // inputsValid = true;

            if (inputsValid) {
                slidePage.style.marginLeft = `-${
                    (100 / stepsNumber) * current
                }%`;
                bullet[current - 1].classList.add("active");
                progressCheck[current - 1].classList.add("active");
                progressText[current - 1].classList.add("active");
                current += 1;
            }
        });
    }

    for (let i = 0; i < prevButtons.length; i++) {
        prevButtons[i].addEventListener("click", function (event) {
            event.preventDefault();
            slidePage.style.marginLeft = `-${
                (100 / stepsNumber) * (current - 2)
            }%`;
            bullet[current - 2].classList.remove("active");
            progressCheck[current - 2].classList.remove("active");
            progressText[current - 2].classList.remove("active");
            current -= 1;
        });
    }
    submitBtn.addEventListener("click", function () {
        bullet[current - 1].classList.add("active");
        progressCheck[current - 1].classList.add("active");
        progressText[current - 1].classList.add("active");
        current += 1;
        setTimeout(function () {
            alert("Your Form Successfully Signed up");
            location.reload();
        }, 800);
    });

    function validateInputs(ths) {
        let inputsValid = true;

        const inputs =
            ths.parentElement.parentElement.querySelectorAll("input");
        for (let i = 0; i < inputs.length; i++) {
            const valid = inputs[i].checkValidity();
            if (!valid) {
                inputsValid = false;
                inputs[i].classList.add("invalid-input");
            } else {
                inputs[i].classList.remove("invalid-input");
            }
        }
        return inputsValid;
    }
}