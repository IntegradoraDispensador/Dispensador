#include <Servo.h>

Servo ser1;

void setup() {

  ser1.attach(9,650,2400);

}

void loop() {

  ser1.write(0);
  delay(3000);
  ser1.write(90);
  delay(3000);

}
