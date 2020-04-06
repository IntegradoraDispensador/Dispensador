#include <Servo.h>
#include <ESP8266WiFi.h>
#include "Adafruit_MQTT.h"
#include "Adafruit_MQTT_Client.h"

#define WLAN_SSID       "MEGACABLE393"
#define WLAN_PASS       "canela200100#"

#define AIO_SERVER      "io.adafruit.com"
#define AIO_SERVERPORT  1883                   // use 8883 for SSL
#define AIO_USERNAME    "ubaldo01"
#define AIO_KEY         "aio_MKUK77bwwjHfVpLvtR6NNFC2Ub5o"
#define AIO_GROUP       "Proyecto"

unsigned long previousMillis = 0;

WiFiClient client;
Servo ser1;
Adafruit_MQTT_Client mqtt(&client, AIO_SERVER, AIO_SERVERPORT, AIO_USERNAME, AIO_KEY);


Adafruit_MQTT_Publish distValue = Adafruit_MQTT_Publish(&mqtt, AIO_USERNAME "/feeds/"AIO_GROUP"ultrasonico");
Adafruit_MQTT_Subscribe ledBrightness = Adafruit_MQTT_Subscribe(&mqtt, AIO_USERNAME "/feeds/"AIO_GROUP"servomotor");

void MQTT_connect();

uint16_t ledBrightValue = 0;
uint16_t valorDistancia = 0;
long tiempoUltimaLectura=0;
const int Trigger = D2;   //Pin digital 2 para el Trigger del sensor
const int Echo = D3;   //Pin digital 3 para el Echo del sensor

void setup() {
  ser1.attach(5);
  ser1.write(0);
  Serial.begin(115200);
  delay(10);
  pinMode(Trigger, OUTPUT); //pin como salida
  pinMode(Echo, INPUT);  //pin como entrada
  digitalWrite(Trigger, LOW);//Inicializamos el pin con 0

  

  Serial.println(F("Adafruit MQTT demo"));

  // Connect to WiFi access point.
  Serial.println(); Serial.println();
  Serial.print("Connecting to ");
  Serial.println(WLAN_SSID);
  WiFi.begin(WLAN_SSID, WLAN_PASS);
  
  Serial.println();

  Serial.println("WiFi connected");
  Serial.println("IP address: "); 
  Serial.println(WiFi.localIP());

  // Setup MQTT subscription for ledBrightness feed.
  // Setup MQTT subscription for ledBrightness feed.
  
  }

void loop() {
  // Ensure the connection to the MQTT server is alive (this will make the first
  // connection and automatically reconnect when disconnected).  See the MQTT_connect
  // function definition further below.
  MQTT_connect();

  // this is our 'wait for incoming subscription packets and callback em' busy subloop
  // try to spend your time here:
    mqtt.processPackets(500);

  // this is our 'wait for incoming subscription packets' busy subloop
  // try to spend your time here
Adafruit_MQTT_Subscribe *subscription;
  while ((subscription = mqtt.readSubscription(200))) {
    //if (subscription == &ledBrightness) {
      Serial.print(F("SERVOMOTOR : "));
      ledBrightValue = atoi((char *)ledBrightness.lastread);
      Serial.println((char *)ledBrightness.lastread);
      //analogWrite(ledPin, ledBrightValue);
      if (strcmp((char *)ledBrightness.lastread, "ON") == 0) {
        ser1.write(0);
          delay(25000);
          ser1.write(150);
          delay(10000);
        Serial.println("ON");
        Serial.println(strcmp((char *)ledBrightness.lastread, "ON"));
        delay(2000);
      }else
      if (strcmp((char *)ledBrightness.lastread, "OFF") == 0) {
          ser1.write(0);
          delay(1000);
          ser1.write(0);
          delay(1000); 
        Serial.println("OFF");
        Serial.println(strcmp((char *)ledBrightness.lastread, "OFF"));
        delay(2000);
      }
            
    //}
  }

  
   if(millis()-tiempoUltimaLectura>5000){
        valorDistancia=distancia(); 
  
        if (! distValue.publish(valorDistancia)) {
          Serial.println(F("Failed Distancia"));
        }  
        if(valorDistancia >= 17)
        {
          Serial.print("El valor de la distancia es: ");
          Serial.print(valorDistancia);
          Serial.print("cm");
          Serial.println(" Recipiente vacío");
        }
        else
        {
          Serial.print("El valor de la distancia es: ");
          Serial.print(valorDistancia);
          Serial.print("cm");
          Serial.println(" Recipiente lleno");
        }
       tiempoUltimaLectura=millis();
    }
             delay(100);
  }
  
  // this is our 'wait for incoming subscription packets' busy subloop
  // try to spend your time here
  
  // Now we can publish stuff!


    //Serial.println(millis()-tiempoUltimaLectura);
   

  
  // ping the server to keep the mqtt connection alive
  // NOT required if you are publishing once every KEEPALIVE seconds
  /*
  if(! mqtt.ping()) {
    mqtt.disconnect();
  }
  */


// Function to connect and reconnect as necessary to the MQTT server.
// Should be called in the loop function and it will take care if connecting.
void MQTT_connect() {
  int8_t ret;

  // Stop if already connected.
  if (mqtt.connected()) {
    return;
  }

  Serial.print("Connecting to MQTT... ");

  uint8_t retries = 3;
  while ((ret = mqtt.connect()) != 0) { // connect will return 0 for connected
       Serial.println(mqtt.connectErrorString(ret));
       Serial.println("Retrying MQTT connection in 5 seconds...");
       mqtt.disconnect();
       delay(5000);  // wait 5 seconds
       retries--;
       if (retries == 0) {
         // basically die and wait for WDT to reset me
         while (1);
       }
  }
  Serial.println("MQTT Connected!");
}

long distancia(){
  long t; //timepo que demora en llegar el eco
  long d; //distancia en centimetros

  digitalWrite(Trigger, HIGH);
  delayMicroseconds(10);          //Enviamos un pulso de 10us
  digitalWrite(Trigger, LOW);
  t = pulseIn(Echo, HIGH); //obtenemos el ancho del pulso
  d = t/59;             //escalamos el tiempo a una distancia en cm
  return d;  
}
