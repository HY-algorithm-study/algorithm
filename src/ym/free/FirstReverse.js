function FirstReverse(str) { 
  var returnString = "";
  for (let i = str.length - 1; i >= 0; i--) {
      returnString += str[i];
  }
  return returnString; 
}
   
// keep this function call here
console.log(FirstReverse("coderbyte"));