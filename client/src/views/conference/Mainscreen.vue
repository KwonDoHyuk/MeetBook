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
      <v-sheet class="my-4">
        <v-slide-group class="pa-1" active-class="success" show-arrows>
          <v-slide-item v-for="n in 20" :key="n" class="ma-2">
            <div>
              <v-menu offset-y>
                <template v-slot:activator="{attrs, on}">
                  <v-card v-bind="attrs" v-on="on">
                    사용자 {{ n }}
                  </v-card>
                </template>
                <v-list>
                  <v-list-item link @click="onOffcamera(n)">
                    화면 끄기
                  </v-list-item>
                  <v-list-item link @click="onMute(n)">
                    음소거
                  </v-list-item>
                  <v-divider></v-divider>
                  <v-list-item link @click="onKick(n)">
                    강퇴
                  </v-list-item>
                </v-list>
              </v-menu>

            </div>



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
          <p v-for="(log, idx) in chatlog" :key="idx">
            {{ log.user }} ({{ log.timestamp }}): <br>{{ log.content }}
          </p>
          
        </v-card>
        <!-- 채팅 폼 -->
        <div class="d-flex flex-row">
          <div class="col-8">
          <v-textarea no-resize v-model="inputChat" @keyup.enter="onInputChat"></v-textarea>
          </div>
          <div class="col-4">
          <v-btn block @click="onInputChat">보내기</v-btn>
          </div>
        </div>
      </div>
      <!-- 기타 조작부 -->
      <v-divider class="my-5"></v-divider>
      <div class="d-flex flex-row justify-space-between">
        <v-btn>
          환경설정
        </v-btn>
        <v-btn color="accent" @click="onExit">
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
      showMenu: false,
      inputChat: '',

      chatlog: [
        {user: '사용자 1', timestamp: '2011-10-05T14:48:00', content: '우리 개발 화이팅'},
        {user: '사용자 1', timestamp: '2011-10-05T14:48:00', content: '백엔드 화이팅'},
        {user: '사용자 1', timestamp: '2011-10-05T14:48:00', content: '백엔드 화이팅'},
        {user: '사용자 1', timestamp: '2011-10-05T14:48:00', content: '백엔드 화이팅'},
        {user: '사용자 1', timestamp: '2011-10-05T14:48:00', content: '백엔드 화이팅'},
        {user: '사용자 1', timestamp: '2011-10-05T14:48:00', content: '백엔드 화이팅'},
        {user: '사용자 1', timestamp: '2011-10-05T14:48:00', content: '백엔드 화이팅'},
        {user: '사용자 1', timestamp: '2011-10-05T14:48:00', content: '프론트엔드 화이팅'},
        {user: '사용자 1', timestamp: '2011-10-05T14:48:00', content: '프론트엔드 화이팅'},
        {user: '사용자 1', timestamp: '2011-10-05T14:48:00', content: '프론트엔드 화이팅'},
        {user: '사용자 1', timestamp: '2011-10-05T14:48:00', content: '프론트엔드 화이팅'},
        {user: '사용자 1', timestamp: '2011-10-05T14:48:00', content: '프론트엔드 화이팅'},
        {user: '사용자 1', timestamp: '2011-10-05T14:48:00', content: '프론트엔드 화이팅'},
      ],
    }
  },

  methods: {
    onOffcamera: function (userid) {
      console.log('offcam' + userid)
    },

    onMute: function (userid) {
      console.log('Mute' + userid)
    },

    onKick: function (userid) {
      console.log('kick' + userid)
    },

    onExit: function () {
      console.log('exit')
    },

    onInputChat: function () {
      if (this.inputChat != '') {
        console.log('input:' + this.inputChat)
        this.inputChat = ''
      }
    },

  },

}
</script>

<style>

</style>