#include <Servo.h>

Servo ser1;

void setup() {

  ser1.attach(9,650,2400);

}

void loop() {

  ser1.write(0);
  delay(25000);
  ser1.write(150);
  delay(10000);

}
