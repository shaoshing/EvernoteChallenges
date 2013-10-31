process.stdin.resume();
process.stdin.setEncoding('utf8');

var numbers = []
// Read the inputs
process.stdin.on('data', function(chunk) {
  var inputs = chunk.split("\n");
  var numberLength = parseInt(inputs.shift());
  for (var i = 0; i < numberLength; i++){
    numbers.push(parseInt(inputs.shift()));
  }
});

// After reading the inputs
process.stdin.on('end', function() {
  var totalProduct = 1, countOfZero = 0;
  for( i in numbers ){
    if(numbers[i] != 0){
      totalProduct *= numbers[i];
    }else{
      countOfZero += 1;
      if(countOfZero == 2)
        break;
    }
  }

  for(i in numbers){
    if (countOfZero == 2){
      console.log(0);
    }else if (countOfZero == 1){
      numbers[i] == 0 ? console.log(totalProduct) : console.log(0);
    }else{
      console.log(totalProduct/numbers[i]);
    }
  }
});

