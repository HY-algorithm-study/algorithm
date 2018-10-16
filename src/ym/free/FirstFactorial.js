function FirstFactorial(num) {
  // if (num === 0) return 1;
  // return num * FirstFactorial(num - 1);

  // dynamic programming
  var arr = [];
  arr[0] = 1;
  for (let index = 1; index < num + 1; index++) {
    arr[index] = arr[index - 1] * index;
  }
  return arr[num];
}

// keep this function call here
// FirstFactorial(readline());
console.log(FirstFactorial(8));
