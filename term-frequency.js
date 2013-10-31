process.stdin.resume();
process.stdin.setEncoding('utf8');

var words = [];
var outputWordLength = 0, wordLength = 0;
// Read the inputs
process.stdin.on('data', function(chunk) {
  var inputs = chunk.split("\n");
  wordLength = parseInt(inputs.shift());
  for (var i = 0; i < wordLength; i++){
    words.push(inputs.shift());
  }
  outputWordLength = parseInt(inputs.shift());
});

// After reading the inputs
process.stdin.on('end', function() {
  var frequencies = {};
  for (var i = 0; i < wordLength; i++){
    var word = words[i];
    frequencies[word] = frequencies[word] || 0;
    frequencies[word]++;
  }

  var sortedWords = Object.keys(frequencies).sort(function(a, b){
    var compareFrequency = frequencies[b] - frequencies[a];
    return compareFrequency == 0 ? a.localeCompare(b) : compareFrequency;
  });

  for(var i = 0; i < outputWordLength; i++){
    console.log(sortedWords[i]);
  }
});
