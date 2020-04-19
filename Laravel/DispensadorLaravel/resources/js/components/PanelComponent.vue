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
                    "X-AIO-Key" : "aio_pHjQ04DaljjLSM6O4dgdWzXO7jNj"
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
                    "X-AIO-Key" : "aio_pHjQ04DaljjLSM6O4dgdWzXO7jNj"
                        },
                data: {
                    value: 'OFF',
                    
                    }
                });   
        },

         aguaOn(){
            axios({
                method: 'post',
                url: 'https://io.adafruit.com/api/v2/GerardoDM/feeds/agua/data',
                params: {
                    "X-AIO-Key" : "aio_EaMk439i6cotrfXp0Prmaev7ncKa"
                        },
                data: {
                    value: 'ON',
                    
                    }
                });   
        },

         aguaOff(){
            axios({
                method: 'post',
                url: 'https://io.adafruit.com/api/v2/GerardoDM/feeds/agua/data',
                params: {
                    "X-AIO-Key" : "aio_EaMk439i6cotrfXp0Prmaev7ncKa"
                        },
                data: {
                    value: 'OFF',
                    
                    }
                });   
        }

    }
    
}
</script>