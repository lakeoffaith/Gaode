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
    async showPicker(stateKey, options) {
        try {
            const {action, minute, hour} = await Indoor.open(options);
            var newState = {};
            if (action === Indoor.timeSetAction) {
                newState[stateKey + 'Text'] = _formatTime(hour, minute);
                newState[stateKey + 'Hour'] = hour;
                newState[stateKey + 'Minute'] = minute;
            } else if (action === Indoor.dismissedAction) {
                newState[stateKey + 'Text'] = 'dismissed';
            }
            this.setState(newState);
        } catch (Error) {
            console.warn(`Error in example '${stateKey}': `, message);
        }
    }
  render() {
    return (
      <View style={styles.container}>

          <TouchableWithoutFeedback
              onPress={this.showPicker.bind(this, 'preset', {
              hour: this.state.presetHour,
              minute: this.state.presetMinute,
            })}>
              <View>
              <Text>请按按钮</Text>
              <Text style={styles.text}>{this.state.presetText}</Text></View>
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
