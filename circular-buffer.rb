class CircularBuffer
  def initialize(buffer_size)
    @buffer = []
    @buffer_max_size = buffer_size
    @buffer_size = 0
    @start_of_buffer = 0
    @end_of_buffer = 0
  end

  def add( items )
    items.each do |item|
      if @buffer_size == 0
        @start_of_buffer = 0
        @end_of_buffer = 0
        @buffer_size = 1
      else
        @end_of_buffer = (@end_of_buffer+1)%@buffer_max_size
        @buffer_size += 1
        if @buffer_size > @buffer_max_size
          @buffer_size = @buffer_max_size
          @start_of_buffer = (@start_of_buffer+1)%@buffer_max_size
        end
      end

      @buffer[@end_of_buffer] = item
    end
  end

  def remove( removalCount )
    return if @buffer_size == 0
    if @buffer_size == removalCount
      @buffer_size = 0
    else
      @start_of_buffer = (@start_of_buffer+removalCount)%@buffer_max_size
      @buffer_size -= removalCount
    end
  end

  def list
    @buffer_size.times do |n|
      index = (@start_of_buffer+n)%@buffer_max_size
      puts @buffer[index]
    end
  end
end

buffer = CircularBuffer.new(gets.to_i)
command = ""
while command = gets do
  case command[0]
  when "A"
    items = []
    command.split(" ")[1].to_i.times do
      items << gets
    end
    buffer.add(items)
  when "R"
    buffer.remove(command.split(" ")[1].to_i)
  when "L"
    buffer.list
  when "Q"
    exit
  end
end
