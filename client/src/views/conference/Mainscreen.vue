<template>
  

  <v-row class="align-stretch">
    <!-- 영상 목록 -->
    <v-col class="col-12 col-md-9 d-flex flex-column">
      <!-- 사용자 화면 -->
      <div>
        <v-card class="ma-4">현재 접속한 사용자 화면</v-card>
      </div>
      <!-- 주 진행자 화면 -->
      <div>
        <v-card class="ma-4" min-height="480px">주 진행자 화면</v-card>
        
      </div>
      <!-- 기타 청취자 화면 -->
      <v-sheet class="mx-auto my-5" elevation="8">
        <v-slide-group class="pa-4" v-model="participants" active-class="success" show-arrows>
          <v-slide-item>
            <v-card class="ma-4">사용자 1</v-card>
          </v-slide-item>
          <v-slide-item>
            <v-card class="ma-4">사용자 2</v-card>
          </v-slide-item>
        </v-slide-group>
      </v-sheet>
      <p>order를 부여해서 배치하거나 세 줄로 나누어 배치하기 (주 진행자가 가운데에 오는 배치)</p>
    </v-col>


    <!-- 영상 외 (오른쪽 화면) -->
    <v-col class="col-12 col-md-3 d-flex flex-column">
      <!-- 채팅 화면 -->
      <div class="d-flex flex-column">
        <!-- 채팅창 -->
        <v-card outlined class="overflow-y-auto ma-4" min-height="560px" max-height="560px">
          <p>사용자 (시간): <br>채팅내용</p>
          <p>사용자 (시간): <br>채팅내용</p>
          <p>사용자 (시간): <br>채팅내용</p>
          <p>사용자 (시간): <br>채팅내용</p>
          <p>사용자 (시간): <br>채팅내용</p>
          <p>사용자 (시간): <br>채팅내용</p>
          <p>사용자 (시간): <br>채팅내용</p>
          <p>사용자 (시간): <br>채팅내용</p>
          <p>사용자 (시간): <br>채팅내용</p>
          <p>사용자 (시간): <br>채팅내용</p>
          <p>사용자 (시간): <br>채팅내용</p>
          <p>사용자 (시간): <br>채팅내용</p>
          <p>사용자 (시간): <br>채팅내용</p>
          <p>사용자 (시간): <br>채팅내용</p>
          
        </v-card>
        <!-- 채팅 폼 -->
        <div class="d-flex flex-row">
          <div class="col-8">
          <v-textarea no-resize></v-textarea>
          </div>
          <div class="col-4">
          <v-btn block>보내기</v-btn>
          </div>
        </div>
      </div>
      <!-- 기타 조작부 -->
      <v-divider class="my-5"></v-divider>
      <div class="d-flex flex-row justify-space-between">
        <v-btn>
          환경설정
        </v-btn>
        <v-btn color="accent">
          나가기
        </v-btn>
      </div>
    </v-col>


  </v-row>
  
</template>

<script>
export default {
  name: 'Mainscreen',
  components: {

  },

  props: {

  },

  data: function () {
    return {
      participants: null,
    }
  },

}
</script>

<style>

</style>