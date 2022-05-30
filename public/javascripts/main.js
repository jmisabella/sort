
function random(min = 0, max = Number.MAX_SAFE_INTEGER) {
  return Math.floor(Math.random() * (max - min) + min);
} 

$(document).ready(function() {
  // screen.lockOrientation('portrait');

  $('#board-size').change(function() {
    var opval = $(this).val();
    if (opval != '') {
      console.log("Sending ...");
      sendInitToServer();
      // $('#board-size').val("");
      // $("#play-pause-button").text("Pause"); 
      $("#play-pause-button").removeClass("play"); 
      $("#play-pause-button").addClass("pause"); 
      window.clearInterval(nextMoveIntervalEvent); 
      nextMoveIntervalEvent = window.setInterval(nextMove, interval); 
    }
  });

  $('#board-size').change(function() {
    // Trigger here your function:    
    console.log('Selected Option: ' + $(this).val() );
    var opval = $(this).val();
    if (opval != '') {
      console.log("Sending ...");
      sendInitToServer();
      // $('#board-size').val("");
      // $("#play-pause-button").text("Pause"); 
      $("#play-pause-button").removeClass("play"); 
      $("#play-pause-button").addClass("pause");

      // create the message as json
      let jsonMessage = {
          message: opval 
      };
      // send our json message to the server
      sendToServer(jsonMessage);

      window.clearInterval(nextMoveIntervalEvent); 
      nextMoveIntervalEvent = window.setInterval(nextMove, interval); 
    
      
    }
  });

  // $('#board-size').click(function() {
  //   if ( $(this).data('clicks') == 1 ) {
  //     // Trigger here your function:    
  //     console.log('Selected Option: ' + $(this).val() );
  //     $(this).data('clicks', 0);
  //     var opval = $(this).val();
  //     if (opval != '') {
  //       console.log("Sending ...");
  //       sendInitToServer();
  //       $('#board-size').val("");
  //     }
  //   } else {
  //     console.log('first click');
  //     $(this).data('clicks', 1);
  //   }
  // });

  // $('#board-size').focusout( function() {
  //   $(this).data('clicks', 0);
  // });
});

function wait(ms){
var start = new Date().getTime();
var end = start;
while(end < start + ms) {
  end = new Date().getTime();
}
}

var webSocket;
var messageInput;

function init() {
var host = location.origin.replace(/^https/, 'wss').replace(/^http/, 'ws'); 
webSocket = new WebSocket(`${host}/ws`);
webSocket.onopen = onOpen;
webSocket.onclose = onClose;
webSocket.onmessage = onMessage;
webSocket.onerror = onError;
$("#message-input").focus();
}

$("#dark-mode").change(function() {
if (this.checked) {
  $("body").addClass("dark-mode");
  $("body").removeClass("light-mode");
} else {
  $("body").addClass("light-mode");
  $("body").removeClass("dark-mode");
}
})

// var sortedTestData = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242];

function displayList(list) {
  consoleLog("LENGTH: " + list.length);
  for(var i = 0, size = list.length; i < size ; i++){
    var divId = ("000" + i.toString()).slice(-3); // left padded 
    var divClass = "bw" + ("000" + list[i].toString().trim()).slice(-3); // left padded
    $("#" + divId).removeClass();
    $("#" + divId).addClass(divClass);
  }
}

function onOpen(event) {
  consoleLog("CONNECTED to server");
}

function onClose(event) {
  consoleLog("DISCONNECTED from server");
  consoleLog("Re-initializing a new fresh connection so server will be available when the next new game's requested");
  init();
}

function onError(event) {
  consoleLog("ERROR: " + event.data);
  consoleLog("ERROR: " + JSON.stringify(event));
}

function onMessage(event) {
  console.log(event.data);
  let receivedData = JSON.parse(event.data);
  console.log("New Data: ", receivedData);
  // get the text from the "body" field of the json we
  // receive from the server.
  if (receivedData.body.startsWith("Invalid")) {
    consoleLog("ERROR: " + receivedData.body); 
    alert(receivedData.body);
  } else {
    $('#remaining-moves').text(receivedData.body);
    nextMove();
  }
}

function consoleLog(message) {
  console.log("New message: ", message);
}

window.addEventListener("load", init, false);

$("#play-pause-button").click(function (e) {
  // displayList(sortedTestData);

if ($("#play-pause-button").attr("class").indexOf("play") == -1 && !$("#play-pause-button").attr("class").indexOf("pause") == -1) {
  $("#play-pause-button").removeClass("pause");
  $("#play-pause-button").addClass("play");
} else if ($("#play-pause-button").attr("class").indexOf("play") != -1) {
  nextMoveIntervalEvent = window.setInterval(nextMove, interval); 
  $("#play-pause-button").removeClass("play");
  $("#play-pause-button").addClass("pause");
} else if ($("#play-pause-button").attr("class").indexOf("pause") != -1) {
  window.clearInterval(nextMoveIntervalEvent); 
  $("#play-pause-button").removeClass("pause");
  $("#play-pause-button").addClass("play");
} else {
  $("#play-pause-button").removeClass("play");
  $("#play-pause-button").removeClass("pause");
} 
});

$("#board").click(function (e) {
  nextMove();
});

var interval = 220;
var nextMoveIntervalEvent = window.setInterval(nextMove, interval);

// continue to step while user presses the any key
$(window).on("keydown", function (e) {
  if (e.which == 13 || e.which == 32 || e.which == 9) { // 13 is <enter>, 32 is <space>, 9 is <tab>
      nextMove();
      return false;
  }
});

$("#remaining-moves").on("ontouchstart", function(e) {
nextMove();
return false; 
});
$("#remaining-moves").on("ontouchmove", function(e) {
nextMove();
return false; 
});
$("#step-button").on("ontouchstart", function(e) {
nextMove();
return false; 
});
$("#step-button").on("ontouchmove", function(e) {
nextMove();
return false; 
});

function sendInitToServer() {
  messageInput = $("#board-size").val();
  // if the trimmed message was blank, return now
  if ($.trim(messageInput) == "") {
      return false;
  }
  // create the message as json
  let jsonMessage = {
      message: messageInput
  };
  // send our json message to the server
  sendToServer(jsonMessage);
}

function sendStepToServer() {
  messageInput = $("#board-size").val();
  // if the trimmed message was blank, return now
  if ($.trim(messageInput) == "") {
      return false;
  }
  // create the message as json
  let jsonMessage = {
      message: messageInput
  };
  // send our json message to the server
  sendToServer(jsonMessage);
}

// send the data to the server using the WebSocket
function sendToServer(jsonMessage) {
  if(webSocket.readyState == WebSocket.OPEN) {
      consoleLog("SENT: " + jsonMessage.message);
      webSocket.send(JSON.stringify(jsonMessage));
  } else {
      consoleLog("Could not send data. Websocket is not open.");
  }
}

// // Convert a given string such as: ((1,2,3),(4,5,6),(7,8,*))
// // into markup as an unordered list
// function boardMarkup(b, gridSize) {
//   // alert("BOARD: " + b);
//   var board = b.replace(' ', '');
//   var markup = '<ul><li>';
//   for (var i = 0; i < board.length; i++) {
//     if (i % gridSize == 0) {
//       markup += '</li><li>';
//     }
//     var curr = board.charAt(i);
//     var next = ''; // next is to look ahead for * so we know to use special css class on its li element
//     if ((i + 1) < board.length) {
//       next = board.charAt(i + 1)
//     }
//     if (next == '*') {
//       if (curr == '(') {
//         markup += "<ul><li class='empty'>";
//       } else if (curr == ')') {
//         markup += "</li></ul>";
//       } else if (curr == ',') {
//         markup += "</li><li class='empty'>";
//       } else {
//         if (curr == "*") {
//           markup += " ";
//         } else {
//           markup += curr
//         }
//       }
//     } else {
//       if (curr == '(') {
//         markup += "<ul><li>";
//       } else if (curr == ')') {
//         markup += "</li></ul>";
//       } else if (curr == ',') {
//         markup += "</li><li>";
//       } else {
//         if (curr == "*") {
//           markup += " ";
//         } else {
//           markup += curr
//         }
//       }
//     }
//   }
//   markup += '</li></ul>';
//   return markup;
// }

function head(lst) {
  return lst[0];
}

function tail(lst) {
  return lst.slice(1);
}

function concatenate(lst, delimiter) {
  var result = ''
  for (i = 0; i < lst.length; i++) {
    if (result.length > 0) {
      result += delimiter
    }
    result += lst[i];
  }
  return result;
}

function nextMove() {
  var moves = $('#remaining-moves').text().split('|')
  var curr = head(moves);
  var remaining = tail(moves);
  var remainingCount = remaining.length;
  if ($('#remaining-moves').text() != 'remaining: 0' && $('#remaining-moves').text() != '') {
    // $('#board').html(boardMarkup(curr)); // TODO: remove this line after we map each list item to a css class

    displayList(curr.split(','));

    $('#remaining-moves').text(concatenate(remaining, '|'));
    $('#remaining-count').text('remaining: ' + remainingCount)
    if (remainingCount == 0) {
      // $("#play-pause-button").text("");
      $("#play-pause-button").removeClass("play");
      $("#play-pause-button").removeClass("pause");
      window.clearInterval(nextMoveIntervalEvent); 
      nextMoveIntervalEvent = window.setInterval(nextMove, interval); 
    }
  }
}

