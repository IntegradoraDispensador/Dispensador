//sensor ultrasonico
const int Trigger = 2;   
const int Echo = 3;   
int ledPin = 8;

void setup() {
  Serial.begin(9600);
  pinMode(Trigger, OUTPUT);
  pinMode(Echo, INPUT); 
  pinMode(ledPin, OUTPUT);  
  digitalWrite(Trigger, LOW);
}

void loop()
{

  long tiempo; 
  long distancia;

  digitalWrite(Trigger, HIGH);
  delayMicroseconds(10);          
  digitalWrite(Trigger, LOW);
  
  tiempo = pulseIn(Echo, HIGH); 
  distancia = tiempo/59;             
  if (distancia >= 16){
     
      Serial.print(distancia);      
      Serial.print("cm  El Recipiente Esta Vacío");
      Serial.println();
      delay(1000);          
    
  }
  else
  {
 Serial.print(distancia);      
      Serial.print("cm  El Recipiente Esta Lleno\n");
      delay(1000);
    }
 
}
