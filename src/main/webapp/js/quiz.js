let seconds=300;
const timer=document.getElementById("timer");
if(timer){
  const interval=setInterval(()=>{
    seconds--;
    const m=Math.floor(seconds/60),s=seconds%60;
    timer.textContent="Time remaining: "+m+":"+String(s).padStart(2,"0");
    if(seconds<=0){clearInterval(interval);document.getElementById("quizForm").submit();}
  },1000);
}
