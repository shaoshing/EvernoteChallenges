
number_length = gets.to_i
numbers = []
number_length.times.each{ numbers << gets.to_i }

count_of_zero = 0
total_product = 1
numbers.each do |number|
  total_product *= number if number != 0
  count_of_zero += 1 if number == 0
  break if count_of_zero > 1
end

numbers.each do |number|
  if count_of_zero > 1
    puts 0
  elsif count_of_zero == 1
    if number == 0
      puts total_product
    else
      puts 0
    end
  else
    puts total_product/number
  end
end
