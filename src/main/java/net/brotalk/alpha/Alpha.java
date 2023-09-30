package net.brotalk.alpha;

import agones.dev.sdk.alpha.SDKGrpc;
import io.grpc.Channel;

import java.util.List;

/**
 * This class contains a list of methods currently in the Alpha feature stage.
 */
public class Alpha {

    private final SDKGrpc.SDKBlockingStub stub;

    public Alpha(Channel channel) {
        this.stub = SDKGrpc.newBlockingStub(channel);
    }

    /**
     * This method retrieves the current player capacity.
     *
     * @return the current value of GameServer.Status.Players.Capacity
     *
     * @see <a href="https://agones.dev/site/docs/guides/client-sdks/#alphasetplayercapacitycount">https://agones.dev/site/docs/guides/client-sdks/</a>
     */
    public long getPlayerCapacity() {
        return stub.getPlayerCapacity(agones.dev.sdk.alpha.Alpha.Empty.getDefaultInstance()).getCount();
    }

    /**
     * Update the GameServer.Status.Players.Capacity value with a new capacity.
     *
     * @param capacity the new capacity to update
     *
     * @see <a href="https://agones.dev/site/docs/guides/client-sdks/#alphasetplayercapacitycount">https://agones.dev/site/docs/guides/client-sdks/</a>
     */
    public void setPlayerCapacity(int capacity) {
        stub.setPlayerCapacity(agones.dev.sdk.alpha.Alpha.Count.newBuilder().setCount(capacity).build());
    }

    /**
     * This method increases the SDK’s stored player count by one,
     * and appends this playerID to GameServer.Status.Players.IDs.
     *
     * @param id playerID
     *
     * @return true and adds the playerID to the list of playerIDs
     *         if this playerID was not already in the list of connected playerIDs.
     *
     * @see <a href="https://agones.dev/site/docs/guides/client-sdks/#alphaplayerconnectplayerid">https://agones.dev/site/docs/guides/client-sdks/</a>
     */
    public boolean playerConnect(String id) {
        return stub.playerConnect(agones.dev.sdk.alpha.Alpha.PlayerID.newBuilder().setPlayerID(id).build()).getBool();
    }

    /**
     * This method decreases the SDK’s stored player count by one,
     * and removes the playerID from GameServer.Status.Players.IDs.
     *
     *
     * @param id playerID
     *
     * @return If the playerID was not in the list of connected playerIDs,
     *         the call will return false, and the connected playerID list
     *         will be left unchanged.
     *
     * @see <a href="https://agones.dev/site/docs/guides/client-sdks/#alphaplayerdisconnectplayerid">https://agones.dev/site/docs/guides/client-sdks/</a>
     */
    public boolean playerDisconnect(String id) {
        return stub.playerDisconnect(agones.dev.sdk.alpha.Alpha.PlayerID.newBuilder().setPlayerID(id).build()).getBool();
    }

    /**
     * This method retrieves the current player count.
     *
     * @return the current player count
     *
     * @see <a href="https://agones.dev/site/docs/guides/client-sdks/#alphagetplayercount">https://agones.dev/site/docs/guides/client-sdks/</a>
     */
    public long getPlayerCount() {
        return stub.getPlayerCount(agones.dev.sdk.alpha.Alpha.Empty.getDefaultInstance()).getCount();
    }

    /**
     * This method returns if the playerID is currently connected
     * to the GameServer.
     *
     * @param playerId the id of a player
     * @return true if the player is found on the gameserver, otherwise false
     *
     * @see <a href="https://agones.dev/site/docs/guides/client-sdks/#alphaisplayerconnectedplayerid">https://agones.dev/site/docs/guides/client-sdks/</a>
     */
    public boolean isPlayerConnected(String playerId) {
        return stub.isPlayerConnected(agones.dev.sdk.alpha.Alpha.PlayerID.newBuilder().setPlayerID(playerId).build()).getBool();
    }

    /**
     * This method returns the list of the currently connected player ids.
     *
     * @return a list of connected players
     *
     * @see <a href="https://agones.dev/site/docs/guides/client-sdks/#alphagetconnectedplayers">https://agones.dev/site/docs/guides/client-sdks/</a>
     */
    public List<String> getPlayerList() {
        return stub.getConnectedPlayers(agones.dev.sdk.alpha.Alpha.Empty.getDefaultInstance()).getListList();
    }
}
