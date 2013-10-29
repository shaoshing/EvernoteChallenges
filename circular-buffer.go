package main

import (
  "fmt"
  "strconv"
  "bufio"
  "os"
)

func main() {
  var bufferSize int16 = 0
  fmt.Scanf("%d", &bufferSize)
  buffer := makeBuffer(bufferSize)

  reader := bufio.NewReader(os.Stdin)
  itemCount := int16(0)
  for {
    command, _ := reader.ReadString('\n')
    command = command[:len(command)-1] // remove trailing linebreak
    if command == "Q"{
      break
    }

    if command != "L"{
      count, _ := strconv.ParseInt(command[2:], 10, 16)
      itemCount = int16(count)
    }

    switch command[:1]{
    case "A":
      items := make([]string, itemCount)
      for i := int16(0); i < itemCount; i++{
        item, _ := reader.ReadString('\n')
        items[i] = item[:len(item)-1] // remove trailing linebreak
      }
      buffer.Add(items)
    case "R":
      buffer.Remove(itemCount)
    case "L":
      for i := int16(0); i < buffer.Size(); i++{
        fmt.Println(buffer.ReadAt(i))
      }
    }
  }
}

type Buffer struct {
  pool []string
  maxSize int16
  size int16
  startOfBuffer int16
  endOfBuffer int16
}

func (this *Buffer) Add(items []string){
  for _, item := range items{
    if this.size == 0{
      this.startOfBuffer = 0
      this.endOfBuffer = 0
    }else{
      if this.size == this.maxSize{
        this.startOfBuffer = (this.startOfBuffer+1)%this.maxSize
        this.size--
      }
      this.endOfBuffer = (this.endOfBuffer+1)%this.maxSize
    }
    this.pool[this.endOfBuffer] = item
    this.size++
  }
}

func (this *Buffer) Remove(count int16){
  for i := int16(0); i < count; i++{
    if this.size == 0{
      return
    }
    this.startOfBuffer = (this.startOfBuffer+1)%this.maxSize
    this.size--
  }
}

func (this *Buffer) ReadAt(index int16) string{
  return this.pool[(this.startOfBuffer+index)%this.maxSize]
}

func (this *Buffer) Size() int16{
  return this.size
}

func makeBuffer(size int16) Buffer{
  return Buffer{
    pool: make([]string, size),
    maxSize: size,
  }
}
