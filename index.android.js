/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
NativeModules,
    TouchableWithoutFeedback
} from 'react-native';
const Indoor=NativeModules.Indoor
class gaode extends Component {
    constructor(){
        super();
        this.state={
            isoFormatText: 'pick a time (24-hour format)',
            presetHour: 4,
            presetMinute: 4,
            presetText: 'pick a time, default: 4:04AM',
            simpleText: 'pick a time',
        }
    }

    async showGaode(){
       Indoor.open();
    }
  render() {
    return (
      <View style={styles.container}>

            <TouchableWithoutFeedback onPress={this.showGaode} style={{marginBottom:20}}>
                <View style={{width:200,height:200,backgroundColor:'blue'}}>
                    <Text>Gaode</Text>
                    </View>
                </TouchableWithoutFeedback>

      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
    text:{
        color:'red'
    }
});
function _formatTime(hour, minute) {
    return hour + ':' + (minute < 10 ? '0' + minute : minute);
}


AppRegistry.registerComponent('gaode', () => gaode);
