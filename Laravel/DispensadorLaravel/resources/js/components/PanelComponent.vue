<template>
        <div class="container">
            <hr>
            <br>
            <button type="button" v-on:click="postOn()" class="btn btn-primary btn-lg">Abrir croquetas</button>
            <button type="button" class="btn btn-secondary btn-lg">Cerrar croquetas</button>
            <button type="button" class="btn btn-primary btn-lg">Abrir agua</button>
            <button type="button" class="btn btn-secondary btn-lg">Cerrar agua</button>
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
                url: '',
                 params: {
                    "X-AIO-Key" : ""
                        },
                data: {
                    value: 'ON',
                    
                    }
                });   
        },

         postOff(){
            axios({
                method: 'post',
                url: '',
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