process.stdin.resume();
process.stdin.setEncoding('utf8');

CircularBuffer = function(size) {
  this.maxSize = size;
  this.size = 0;
  this.buffer = [];
  this.startOfBuffer = 0;
  this.endOfBuffer = 0;
};

CircularBuffer.prototype.add = function(items){
  for (var i in items){
    if (this.size == 0){
      this.startOfBuffer = 0;
      this.endOfBuffer = 0;
      this.size = 1;
    }else{
      this.endOfBuffer = (this.endOfBuffer+1)%this.maxSize;
      if (this.endOfBuffer == this.startOfBuffer){
        this.startOfBuffer = (this.startOfBuffer+1)%this.maxSize;
        this.size --;
      }
      this.size++;
    }
    this.buffer[this.endOfBuffer] = items[i];
  }
}

CircularBuffer.prototype.remove = function(removalCount){
  this.startOfBuffer = (this.startOfBuffer+removalCount)%this.maxSize;
  this.size -= removalCount;
}

CircularBuffer.prototype.list = function(){
  for (var i = 0; i < this.size; i++) {
    console.log(this.buffer[(this.startOfBuffer+i)%this.maxSize]);
  };
}

var commands = [];
var bufferSize;
var buffer;
// Read the inputs
process.stdin.on('data', function(chunk) {
  var inputs = chunk.split("\n");
  bufferSize = parseInt(inputs.shift());
  commands = inputs;
  buffer = new CircularBuffer(bufferSize);
});

// After reading the inputs
process.stdin.on('end', function() {
  while(true){
    var command = commands.shift().split(" ");
    if (command[0] == "Q"){
      break;
    }
    switch(command[0]){
      case "A":
        var itemCount = parseInt(command[1]);
        var items = []
        for (var i = 0; i < itemCount; i++) {
          items.push(commands.shift());
        };
        buffer.add(items);
        break;
      case "R":
        var removalCount = parseInt(command[1]);
        buffer.remove(removalCount);
        break;
      case "L":
        buffer.list();
        break;
    }
  }
});
