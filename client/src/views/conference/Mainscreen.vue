<template>
  <div>

    <!-- 입장 대화상자 -->
    <v-dialog v-model="yetSession" max-width="320px">
      <v-card class="pa-3">
        <v-card-title>{{conference.title}}</v-card-title>
        <p class="text-center">
          회의에 입장합니다.
        </p>

        <v-spacer></v-spacer>

        <div class="d-flex justify-space-around">
          <v-btn color="primary" class="my-5" @click="joinSession(); yetSession = false">
            입장
          </v-btn>
          <v-btn class="my-5" :to="{name: 'Home'}">
            취소
          </v-btn>
        </div>
      </v-card>
    </v-dialog>

    <!-- 회의 화면은 위 대화상자를 통해 입장한 다음 표시됩니다 -->
    <v-row class="align-stretch" v-if="!yetSession">

      <!-- 영상 목록 -->
      <v-col class="col-12 col-md-9 d-flex flex-column">

        <!-- 사용자 화면 -->
        <div>
          <v-card class="ma-4">현재 접속한 사용자 화면
            <UserVideo :stream-manager="mainStreamManager" class="current-user-video"/>

            <v-btn v-show="publisher.publishVideo" @click="publisher.publishVideo = false">화면 끄기</v-btn>
            <v-btn v-show="!publisher.publishVideo" @click="publisher.publishVideo = true">화면 켜기</v-btn>
            <v-btn v-show="publisher.publishAudio" @click="publisher.publishAudio = false">음소거</v-btn>
            <v-btn v-show="!publisher.publishAudio" @click="publisher.publishAudio = true">소리 켜기</v-btn>
          </v-card>
        </div>
        <!-- 주 진행자 화면 -->
        <div>
          <v-card class="ma-4" min-height="480px">주 진행자 화면</v-card>
        </div>
        <!-- 기타 청취자 화면 -->
        <v-sheet class="my-4">
          <v-slide-group class="pa-1" active-class="success" show-arrows>
            <v-slide-item v-for="sub in subscribers" :key="sub.stream.connection.connectionId" class="ma-2">
              <div>
                <v-menu offset-y>
                  <template v-slot:activator="{attrs, on}">
                    <UserVideo :stream-manager="sub" />
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

        
        <Chat />
        <!-- 채팅 화면 -->
        <div class="d-flex flex-column">
          <!-- 채팅창 -->
          <v-card outlined class="overflow-y-auto ma-4 chatlogbox" min-height="560px" max-height="560px">
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
          <v-btn color="accent" @click="leaveSession  ">
            나가기
          </v-btn>
        </div>
      </v-col>


    </v-row>



  </div>
  

  
</template>

<script>
import axios from 'axios'
import { OpenVidu } from 'openvidu-browser'
import UserVideo from '@/components/conference/UserVideo'
import Chat from '@/components/conference/Chat'

// OpenVidu 접속 설정
// 추후에 따로 빼어서 모듈화, 환경변수화해야 할 부분입니다.
const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443"
const OPENVIDU_SERVER_SECRET = 'MY_SECRET'


export default {
  name: 'Mainscreen',
  components: {
    Chat, UserVideo,
  },

  props: {
  
  },

  data: function () {
    return {

      OV: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],
      mySessionId: null,
      myUserName: 'Username',

      yetSession: true,
      session: undefined,
      
      participants: null,
      showMenu: false,
      
      conference: {

      },
      
      
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

  created: function () {
    this.mySessionId = String(this.$route.params.conferenceId)
    
    // 테스트용 데이터 탑재
    this.conference = {
      title: '회의 제목',

    }
  },

  methods: {
    joinSession: function () {
      this.OV = new OpenVidu()
      this.session = this.OV.initSession()
      this.session.on('streamCreated', ({ stream }) => {
        const subscriber = this.session.subscribe(stream)
        this.subscribers.push(subscriber)
      })

      this.session.on('streamDestroyed', ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0)
        if (index >= 0) {
          this.subscribers.splice(index, 1)
        }
      })

      this.session.on('exception', ({ exception }) => {
        console.warn(exception)
      })

      // user token
      this.getToken(this.mySessionId).then(token => {
        this.session.connect(token, { clientData: this.myUserName})
          .then(() => {
            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
							videoSource: undefined, // The source of video. If undefined default webcam
							publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
							publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
							resolution: '640x480',  // The resolution of your video
							frameRate: 30,			// The frame rate of your video
							insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
							mirror: false       	// Whether to mirror your local video or not
            })

            this.mainStreamManager = publisher
            this.publisher = publisher

            this.session.publish(this.publisher)
          })
          .catch(error => {
            console.log('There was an error connecting to the session:', error.code, error.message);
          })
      })

      window.addEventListener('beforeunload', this.leaveSession)
    },

    // 위까지가 joinSession

    leaveSession () {
      if (this.session) this.session.disconnect()

      this.session = undefined;
			this.mainStreamManager = undefined;
			this.publisher = undefined;
			this.subscribers = [];
			this.OV = undefined;

      window.removeEventListener('beforeunload', this.leaveSession);
      this.$router.push({ name: 'Home'})
    },

    // 여기까지 leaveSession

    updateMainVideoStreamManager (stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

    // 여기서부터 server와 상호작용
    getToken (mySessionId) {      
      return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId))
    },

    createSession (sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
            customSessionId: sessionId,
          }), {
            auth: {
              username: 'OPENVIDUAPP',
              password: OPENVIDU_SERVER_SECRET,
            },
          })
          .then(response => response.data)
          .then(data => resolve(data.id))
          .catch(error => {
            if(error.response.status === 409) {
              resolve(sessionId)
            } else {
              console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
							reject(error.response);
            }
          })
      })
    },

    //여기까지 createSession

    createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
		},



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

        this.chatlog.push({user: '지금 사용자', timestamp: '지금 시간', content: this.inputChat})
        this.inputChat = ''
      }
    },

  },

  watch: {
    chatlog: function () {
      const chatlogbox = document.querySelector('.chatlogbox')
      chatlogbox.scrollTop = chatlogbox.scrollHeight
    },
  }


}
</script>

<style>
  
</style>