<template>
        <div class="container">
            <hr>
            <br>
            <button type="button" v-on:click="postOn()" class="btn btn-primary btn-lg">Abrir croquetas</button>
            <button type="button" v-on:click="postOff()" class="btn btn-secondary btn-lg">Cerrar croquetas</button>
            <button type="button" v-on:click="aguaOn()" class="btn btn-primary btn-lg">Abrir agua</button>
            <button type="button" v-on:click="aguaOff()" class="btn btn-secondary btn-lg">Cerrar agua</button>
            <br>
            <hr>
      
        </div>
</template>

<script>
export default {
    methods: {

        postOn(){
            axios({
                method: 'post',
                url: 'https://io.adafruit.com/api/v2/ubaldo01/feeds/onoff/data',
                 params: {
                    "X-AIO-Key" : "aio_KfQC74y4LVhmdquHPpXkN0yYbEde"
                        },
                data: {
                    "value" : 'ON',
                    
                    }
                });   
        },

         postOff(){
            axios({
                method: 'post',
                url: 'https://io.adafruit.com/api/v2/ubaldo01/feeds/onoff/data',
                params: {
                    "X-AIO-Key" : ""
                        },
                data: {
                    value: 'OFF',
                    
                    }
                });   
        },

         aguaOn(){
            axios({
                method: 'post',
                url: 'https://io.adafruit.com/api/v2/ubaldo01/feeds/onoff/data',
                params: {
                    "X-AIO-Key" : ""
                        },
                data: {
                    value: 'ON',
                    
                    }
                });   
        },

         aguaOff(){
            axios({
                method: 'post',
                url: 'https://io.adafruit.com/api/v2/ubaldo01/feeds/onoff/data',
                params: {
                    "X-AIO-Key" : ""
                        },
                data: {
                    value: 'OFF',
                    
                    }
                });   
        }

    }
    
}
</script>