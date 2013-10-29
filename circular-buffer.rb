buffer = []
buffer_max_size = gets.to_i
buffer_size = 0
buffer_is_empty = true
start_of_buffer = 0
end_of_buffer = 0

command = ""
while command = gets do
  case command[0]
  when "A"
    addCount = command.split(" ")[1].to_i
    addCount.times do
      if buffer_is_empty
        start_of_buffer = 0
        end_of_buffer = 0
        buffer_is_empty = false
        buffer_size = 1
      else
        end_of_buffer = (end_of_buffer+1)%buffer_max_size
        buffer_size += 1
        if buffer_size > buffer_max_size
          buffer_size = buffer_max_size
          start_of_buffer = (start_of_buffer+1)%buffer_max_size
        end
      end

      item = gets
      buffer[end_of_buffer] = item
    end
  when "R"
    next if buffer_is_empty
    removalCount = command.split(" ")[1].to_i
    if buffer_size == removalCount
      buffer_is_empty = true
      buffer_size = 0
    else
      start_of_buffer = (start_of_buffer+removalCount)%buffer_max_size
      buffer_size -= removalCount
    end
  when "L"
    next if buffer_is_empty
    buffer_size.times do |n|
      index = (start_of_buffer+n)%buffer_max_size
      puts buffer[index]
    end
  when "Q"
    exit
  end
end
